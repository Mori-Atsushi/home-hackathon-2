package server

import (
	"context"
	"errors"

	"com.home-hackathon-2/backend/domain"
	"google.golang.org/grpc/metadata"
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

func toDomainAuth(ctx context.Context) (domain.Auth, error) {
	md, _ := metadata.FromIncomingContext(ctx)
	accessTokenList := md.Get("accessToken")
	userIdList := md.Get("userId")
	if len(accessTokenList) == 0 || len(userIdList) == 0 {
		return domain.Auth{}, errors.New("require auth")
	}
	return domain.NewAuth(accessTokenList[0], userIdList[0]), nil
}
