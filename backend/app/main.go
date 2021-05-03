package main

import (
	"context"
	"fmt"
	"log"
	"net"

	pb "local.packages/gen"

	"com.home-hackathon-2/backend/database"
	_ "github.com/go-sql-driver/mysql"
	"github.com/google/uuid"
	"github.com/jmoiron/sqlx"
	"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/reflection"
	"google.golang.org/grpc/status"
)

var db *sqlx.DB
var mySQLConnectionData *database.MySQLConnectionEnv

type server struct {
	pb.UnimplementedAppServiceServer
}

type User struct {
	ID          string `db:"uuid" json:"uuid"`
	Name        string `db:"name" json:"name"`
	AccessToken string `db:"access_token" json:"access_token"`
}

func (*server) CreateUser(ctx context.Context, req *pb.CreateUserRequest) (*pb.CreateUserResponse, error) {
	name := req.GetName()
	uuidForKey := uuid.Must(uuid.NewRandom()).String()
	accessToken := uuid.Must(uuid.NewRandom()).String()

	tx, err := db.Begin()
	if err != nil {
		return nil, status.Errorf(codes.Unavailable, "DB not launched")
	}
	defer tx.Rollback()

	_, err = tx.Exec("INSERT INTO user(uuid, name, access_token) VALUES(?,?,?)", uuidForKey, name, accessToken)
	if err != nil {
		return nil, status.Errorf(codes.Unavailable, "Cannot insert data")
	}
	if err := tx.Commit(); err != nil {
		return nil, status.Errorf(codes.Unavailable, "Cannot insert data")
	}

	res := &pb.CreateUserResponse{
		UserWithAuth: &pb.UserWithAuth{
			User: &pb.User{
				Id:   uuidForKey,
				Name: name,
			},
			AccessToken: accessToken,
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
	mySQLConnectionData = database.NewMySQLConnectionEnv()
	db, err = mySQLConnectionData.ConnectDB()
	if err != nil {
		log.Fatalf("failed to load db: %v", err)
	}
	db.SetMaxOpenConns(10)
	defer db.Close()
	s := grpc.NewServer()
	pb.RegisterAppServiceServer(s, &server{})
	reflection.Register(s)
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
