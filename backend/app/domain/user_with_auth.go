package domain

import (
	"github.com/google/uuid"
)

type UserWithAuth struct {
	User        User
	AccessToken string
}

func CreateUserWithAuth(name string) UserWithAuth {
	return UserWithAuth{
		User:        CreateUser(name),
		AccessToken: uuid.Must(uuid.NewRandom()).String(),
	}
}
