package domain

type ChatRoomQuery struct {
	Type     ChatRoomQueryType
	ChatSend ChatSendQuery
}

func NewChatSendQuery(message string) ChatRoomQuery {
	return ChatRoomQuery{
		Type:     ChatSend,
		ChatSend: ChatSendQuery{Message: message},
	}
}

type ChatRoomQueryType int

const (
	ChatSend ChatRoomQueryType = iota
)

type ChatSendQuery struct {
	Message string
}
