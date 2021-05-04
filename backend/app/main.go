package main

import (
	"context"
	"fmt"
	"log"
	"net"

	pb "local.packages/gen"

	"com.home-hackathon-2/backend/database"
	"com.home-hackathon-2/backend/registory"
	_ "github.com/go-sql-driver/mysql"
	"github.com/jmoiron/sqlx"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

var db *sqlx.DB
var mySQLConnectionData *database.MySQLConnectionEnv
var r *registory.Registory

type server struct {
	pb.UnimplementedAppServiceServer
}

func (*server) CreateUser(ctx context.Context, req *pb.CreateUserRequest) (*pb.CreateUserResponse, error) {
	userService := r.GetUserService()
	name := req.GetName()
	userWithAuth := userService.Create(name)

	res := &pb.CreateUserResponse{
		UserWithAuth: &pb.UserWithAuth{
			User: &pb.User{
				Id:   userWithAuth.User.ID,
				Name: userWithAuth.User.Name,
			},
			AccessToken: userWithAuth.AccessToken,
		},
	}
	return res, nil
}

func (*server) ChatRoomEvent(pb.AppService_ChatRoomEventServer) error {

	return nil
}

func main() {
	fmt.Println("Launch app🚀")
	lis, err := net.Listen("tcp", "0.0.0.0:5300")
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	registory, err := registory.NewRegistory()
	if err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
	r = registory
	s := grpc.NewServer()
	pb.RegisterAppServiceServer(s, &server{})
	reflection.Register(s)
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
