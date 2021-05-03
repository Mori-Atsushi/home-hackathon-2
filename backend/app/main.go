package main

import (
	"context"
	"fmt"
	"log"
	"net"

	pb "local.packages/gen"

	"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/reflection"
	"google.golang.org/grpc/status"
)

type server struct {
	pb.UnimplementedAppServiceServer
}

func (*server) CreateUser(ctx context.Context, req *pb.CreateUserRequest) (*pb.CreateUserResponse, error) {
	name := req.GetName()
	user := pb.User{
		Id:   "id",
		Name: name,
	}
	res := &pb.CreateUserResponse{
		UserWithAuth: &pb.UserWithAuth{
			User:        &user,
			AccessToken: "ACCESS_TOKEN",
		},
	}
	return res, nil
}
func (*server) ChatRoomEvent(pb.AppService_ChatRoomEventServer) error {
	return status.Errorf(codes.Unimplemented, "method ChatRoomEvent not implemented")
}

func main() {
	fmt.Println("Launch app🚀")
	lis, err := net.Listen("tcp", "0.0.0.0:5300")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()
	pb.RegisterAppServiceServer(s, &server{})
	reflection.Register(s)
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
