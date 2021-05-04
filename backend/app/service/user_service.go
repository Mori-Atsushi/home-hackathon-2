package service

import (
	"com.home-hackathon-2/backend/domain"
	"com.home-hackathon-2/backend/repository"
)

type UserService interface {
	Create(name string) domain.UserWithAuth
}

type UserServiceImpl struct {
	repository repository.UserRepository
}

func NewUserServiceImpl(
	userRepository repository.UserRepository,
) *UserServiceImpl {
	return &UserServiceImpl{
		repository: userRepository,
	}
}

func (u *UserServiceImpl) Create(name string) domain.UserWithAuth {
	user := domain.NewUserWithAuth(name)
	u.repository.Save(user)
	return user
}
