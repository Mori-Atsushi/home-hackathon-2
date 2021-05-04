package server

import (
	"com.home-hackathon-2/backend/domain"
	pb "local.packages/gen"
)

func toPBUser(user domain.User) *pb.User {
	return &pb.User{
		Id:   user.ID,
		Name: user.Name,
	}
}

func toPBUserWithAuth(userWithAuth domain.UserWithAuth) *pb.UserWithAuth {
	return &pb.UserWithAuth{
		User:        toPBUser(userWithAuth.User),
		AccessToken: userWithAuth.AccessToken,
	}
}
