package domain

import (
	"github.com/google/uuid"
)

type UserWithAuth struct {
	User        User
	AccessToken string
}

func NewUserWithAuth(name string) UserWithAuth {
	return UserWithAuth{
		User:        NewUser(name),
		AccessToken: uuid.Must(uuid.NewRandom()).String(),
	}
}
