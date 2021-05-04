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
	query := make(chan domain.ChatRoomQuery)
	event := make(chan domain.ChatRoomEvent)
	err = roomService.Join(auth, query, event)
	if err != nil {
		return err
	}
	return nil
}
