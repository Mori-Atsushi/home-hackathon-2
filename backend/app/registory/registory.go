package registory

import (
	"com.home-hackathon-2/backend/service"
)

type Registory struct {
	userService service.UserService
}

func NewRegistory() *Registory {
	return &Registory{
		userService: service.NewUserServiceImpl(),
	}
}

func (r *Registory) GetUserService() service.UserService {
	return r.userService
}
