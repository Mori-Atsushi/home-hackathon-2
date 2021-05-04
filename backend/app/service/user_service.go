package service

import (
	"com.home-hackathon-2/backend/domain"
)

type UserService interface {
	Create(name string) domain.UserWithAuth
}

type UserServiceImpl struct{}

func NewUserServiceImpl() *UserServiceImpl {
	return &UserServiceImpl{}
}

func (u *UserServiceImpl) Create(name string) domain.UserWithAuth {
	return domain.NewUserWithAuth(name)
}
