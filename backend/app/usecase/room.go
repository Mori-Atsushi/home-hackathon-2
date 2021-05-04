package usecase

import (
	"log"

	"com.home-hackathon-2/backend/model"
	pb "local.packages/gen"
)

func sendEvent(room *model.Room, srv pb.AppService_EventServer) {
	for {
		req, err := srv.Recv()
		if err != nil {
			log.Printf("close: %v", req.GetChat().GetUser().GetName())
			break
		}
		room.SendChatEvent(req.GetChat())
	}
}

func receiveEvent(room *model.Room, chat model.Chat, srv pb.AppService_EventServer) {
	channel := room.ReceiveEvent(chat)
	for {
		event, ok := <-channel
		if !ok {
			break
		}
		resp := event.GetRaw()
		error := srv.Send(resp)
		if error != nil {
			break
		}
	}
}
