package registory

import (
	"com.home-hackathon-2/backend/repository"
	"com.home-hackathon-2/backend/service"
)

type Registory struct {
	userService service.UserService
}

func NewRegistory() *Registory {
	userRepository := repository.NewUserRepositoryImpl()
	userService := service.NewUserServiceImpl(userRepository)
	return &Registory{
		userService: userService,
	}
}

func (r *Registory) GetUserService() service.UserService {
	return r.userService
}
