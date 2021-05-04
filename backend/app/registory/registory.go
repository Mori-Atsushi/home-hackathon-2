package registory

import (
	"com.home-hackathon-2/backend/database"
	"com.home-hackathon-2/backend/repository"
	"com.home-hackathon-2/backend/service"
	"github.com/jmoiron/sqlx"
)

type Registory struct {
	userService service.UserService
}

func NewRegistory() (*Registory, error) {
	db, err := setupDB()
	if err != nil {
		return nil, err
	}

	userRepository := repository.NewUserRepositoryImpl(db)
	userService := service.NewUserServiceImpl(userRepository)
	return &Registory{
		userService: userService,
	}, nil
}

func (r *Registory) GetUserService() service.UserService {
	return r.userService
}

func setupDB() (*sqlx.DB, error) {
	mySQLConnectionData := database.NewMySQLConnectionEnv()
	mySQLConnectionData = database.NewMySQLConnectionEnv()
	db, err := mySQLConnectionData.ConnectDB()
	if err != nil {
		return nil, err
	}
	db.SetMaxOpenConns(10)
	defer db.Close()
	return db, nil
}
