package repository

import (
	"com.home-hackathon-2/backend/domain"
	"github.com/jmoiron/sqlx"
)

type UserRepository interface {
	Save(user domain.UserWithAuth) error
	Get(auth domain.Auth) (domain.User, error)
}

type UserRepositoryImpl struct {
	db *sqlx.DB
}

func NewUserRepositoryImpl(db *sqlx.DB) *UserRepositoryImpl {
	return &UserRepositoryImpl{
		db: db,
	}
}

func (r *UserRepositoryImpl) Save(user domain.UserWithAuth) error {
	tx, err := r.db.Begin()
	if err != nil {
		return err
	}
	defer tx.Rollback()

	_, err = tx.Exec("INSERT INTO user(uuid, name, access_token) VALUES(?,?,?)", user.User.ID, user.User.Name, user.AccessToken)
	if err != nil {
		return err
	}
	err = tx.Commit()
	if err != nil {
		return err
	}
	return nil
}

func (r *UserRepositoryImpl) Get(auth domain.Auth) (domain.User, error) {
	tx, err := r.db.Begin()
	if err != nil {
		return domain.User{}, err
	}
	defer tx.Rollback()

	var uuid string
	var name string
	row := tx.QueryRow("SELECT uuid, name FROM user WHERE uuid = ? AND name = ?", auth.UserID, auth.AccessToken)
	err = row.Scan(&uuid, name)
	if err != nil {
		return domain.User{}, err
	}
	return domain.NewUser(uuid, name), nil
}
