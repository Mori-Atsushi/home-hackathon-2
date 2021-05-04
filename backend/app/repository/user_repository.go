package repository

import "com.home-hackathon-2/backend/domain"

type UserRepository interface {
	Save(user domain.UserWithAuth)
}

type UserRepositoryImpl struct{}

func NewUserRepositoryImpl() *UserRepositoryImpl {
	return &UserRepositoryImpl{}
}

func (r *UserRepositoryImpl) Save(user domain.UserWithAuth) {
}
