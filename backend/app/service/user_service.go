package service

import (
	"com.home-hackathon-2/backend/domain"
	"com.home-hackathon-2/backend/repository"
)

type UserService interface {
	Create(name string) (domain.UserWithAuth, error)
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

func (u *UserServiceImpl) Create(name string) (domain.UserWithAuth, error) {
	user := domain.CreateUserWithAuth(name)
	err := u.repository.Save(user)
	if err != nil {
		return domain.UserWithAuth{}, err
	}
	return user, nil
}
