package registory

import (
	"com.home-hackathon-2/backend/database"
	"com.home-hackathon-2/backend/repository"
	"com.home-hackathon-2/backend/service"
	_ "github.com/go-sql-driver/mysql"
	"github.com/jmoiron/sqlx"
)

type Registory struct {
	userService service.UserService
	roomService service.RoomService
}

func NewRegistory() (*Registory, error) {
	db, err := setupDB()
	if err != nil {
		return nil, err
	}

	userRepository := repository.NewUserRepositoryImpl(db)
	userService := service.NewUserServiceImpl(userRepository)
	roomService := service.NewRoomServiceImpl(userRepository)
	return &Registory{
		userService: userService,
		roomService: roomService,
	}, nil
}

func (r *Registory) GetUserService() service.UserService {
	return r.userService
}

func (r *Registory) GetRoomService() service.RoomService {
	return r.roomService
}

func setupDB() (*sqlx.DB, error) {
	mySQLConnectionData := database.NewMySQLConnectionEnv()
	db, err := mySQLConnectionData.ConnectDB()
	if err != nil {
		return nil, err
	}
	db.SetMaxOpenConns(10)
	return db, nil
}
