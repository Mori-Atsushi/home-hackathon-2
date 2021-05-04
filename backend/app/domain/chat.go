package domain

type Chat struct {
	User    User
	Message string
}

func NewChat(user User, message string) Chat {
	return Chat{
		User:    user,
		Message: message,
	}
}
