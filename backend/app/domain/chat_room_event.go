package domain

type ChatRoomEvent struct {
	ChatRecieve ChatRecieveEvent
}

type ChatRecieveEvent struct {
	Chat Chat
}
