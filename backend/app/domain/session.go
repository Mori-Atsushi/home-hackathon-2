package domain

import "github.com/google/uuid"

type Session struct {
	ID string
}

func NewSession() Session {
	return Session{
		ID: uuid.Must(uuid.NewRandom()).String(),
	}
}
