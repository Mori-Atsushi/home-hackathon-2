package model

import pb "local.packages/gen"

type Event struct {
	raw *pb.ChatRoomEventResponse
}

func NewChatEvent(chat *pb.Chat) Event {
	raw := &pb.ChatRoomEventResponse{
		EventOneof: &pb.ChatRoomEventResponse_ChatRecieveResponse{
			ChatRecieveResponse: &pb.ChatReceiveResponse{
				Chat: chat,
			},
		},
	}
	return Event{
		raw: raw,
	}
}

func (e Event) GetRaw() *pb.ChatRoomEventResponse {
	return e.raw
}
