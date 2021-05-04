package main

import (
	"fmt"
	"log"
	"net"

	pb "local.packages/gen"

	"com.home-hackathon-2/backend/registory"
	"com.home-hackathon-2/backend/server"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

func main() {
	fmt.Println("Launch app🚀")
	lis, err := net.Listen("tcp", "0.0.0.0:5300")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	reg, err := registory.NewRegistory()
	if err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
	s := grpc.NewServer()
	pb.RegisterAppServiceServer(s, server.NewServer(reg))
	reflection.Register(s)
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
