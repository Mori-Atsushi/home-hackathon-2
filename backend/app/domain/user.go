package domain

import (
	"github.com/google/uuid"
)

type User struct {
	ID   string
	Name string
}

func CreateUser(name string) User {
	return User{
		ID:   uuid.Must(uuid.NewRandom()).String(),
		Name: name,
	}
}

func NewUser(id string, name string) User {
	return User{
		ID:   id,
		Name: name,
	}
}
