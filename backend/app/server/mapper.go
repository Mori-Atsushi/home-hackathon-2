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

func toPBChat(chat domain.Chat) *pb.Chat {
	return &pb.Chat{
		User:    toPBUser(chat.User),
		Message: chat.Message,
	}
}

func toPBEvent(event domain.ChatRoomEvent) *pb.ChatRoomEventResponse {
	return &pb.ChatRoomEventResponse{
		EventOneof: &pb.ChatRoomEventResponse_ChatRecieveResponse{
			ChatRecieveResponse: &pb.ChatReceiveResponse{
				Chat: toPBChat(event.ChatRecieve.Chat),
			},
		},
	}
}

func toDomainQuery(req *pb.ChatRoomEventRequest) (domain.ChatRoomQuery, error) {
	var query domain.ChatRoomQuery
	switch req.EventOneof.(type) {
	case *pb.ChatRoomEventRequest_SendChatRequest:
		query = domain.ChatRoomQuery{}
	default:
		return domain.ChatRoomQuery{}, errors.New("unkown query")
	}
	return query, nil
}

func toDomainAuth(ctx context.Context) (domain.Auth, error) {
	md, _ := metadata.FromIncomingContext(ctx)
	accessTokenList := md.Get("accessToken")
	userIdList := md.Get("userId")
	if len(accessTokenList) == 0 || len(userIdList) == 0 {
		return domain.Auth{}, errors.New("require auth")
	}
	return domain.NewAuth(userIdList[0], accessTokenList[0]), nil
}
