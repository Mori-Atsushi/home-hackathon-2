package domain

type ChatRoomQuery struct {
	ChatSend ChatSendQuery
}

type ChatSendQuery struct {
	Message string
}
