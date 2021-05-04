package domain

type ChatRoomEvent struct {
	Type        ChatRoomEventType
	ChatRecieve ChatRecieveEvent
}

func NewChatRecieveEvent(chat Chat) ChatRoomEvent {
	return ChatRoomEvent{
		Type:        ChatRecieve,
		ChatRecieve: ChatRecieveEvent{Chat: chat},
	}
}

type ChatRoomEventType int

const (
	ChatRecieve ChatRoomEventType = iota
)

type ChatRecieveEvent struct {
	Chat Chat
}
