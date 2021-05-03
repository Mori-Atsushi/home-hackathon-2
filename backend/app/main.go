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

func (*server) CreateUser(context.Context, *pb.CreateUserRequest) (*pb.CreateUserResponse, error) {
	return nil, status.Errorf(codes.Unimplemented, "method CreateUser not implemented")
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
