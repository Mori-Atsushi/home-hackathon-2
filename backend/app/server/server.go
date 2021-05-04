package server

import (
	"context"

	"com.home-hackathon-2/backend/domain"
	"com.home-hackathon-2/backend/registory"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
	pb "local.packages/gen"
)

type Server struct {
	pb.UnimplementedAppServiceServer
	reg *registory.Registory
}

func NewServer(reg *registory.Registory) *Server {
	return &Server{
		reg: reg,
	}
}

func (s *Server) CreateUser(ctx context.Context, req *pb.CreateUserRequest) (*pb.CreateUserResponse, error) {
	userService := s.reg.GetUserService()
	name := req.GetName()
	userWithAuth, err := userService.Create(name)
	if err != nil {
		return nil, status.Errorf(codes.Unavailable, err.Error())
	}

	res := &pb.CreateUserResponse{
		UserWithAuth: toPBUserWithAuth(userWithAuth),
	}
	return res, nil
}

func (s *Server) ChatRoomEvent(req pb.AppService_ChatRoomEventServer) error {
	roomService := s.reg.GetRoomService()
	auth, err := toDomainAuth(req.Context())
	if err != nil {
		return err
	}
	query := makeQuery(req)
	event := makeEvent(req)
	err = roomService.Join(auth, query, event)
	if err != nil {
		return err
	}
	return nil
}

func makeQuery(req pb.AppService_ChatRoomEventServer) <-chan domain.ChatRoomQuery {
	channel := make(chan domain.ChatRoomQuery, 5)
	go func() {
		for {
			req, err := req.Recv()
			if err != nil {
				break
			}
			query, err := toDomainQuery(req)
			if err == nil {
				channel <- query
			}
		}
		close(channel)
	}()
	return channel
}

func makeEvent(req pb.AppService_ChatRoomEventServer) chan<- domain.ChatRoomEvent {
	channel := make(chan domain.ChatRoomEvent, 5)
	go func() {
		for {
			event, ok := <-channel
			if !ok {
				break
			}
			pb := toPBEvent(event)
			err := req.Send(pb)
			if err != nil {
				break
			}
		}
		close(channel)
	}()
	return channel
}
