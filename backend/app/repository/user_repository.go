package repository

import (
	"com.home-hackathon-2/backend/domain"
	"github.com/jmoiron/sqlx"
)

type UserRepository interface {
	Save(user domain.UserWithAuth) error
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

	_, err = tx.Exec("INSERT INTO user(uuid, name, access_token) VALUES(?,?,?)", uuidForKey, name, accessToken)
	if err != nil {
		return err
	}
	err = tx.Commit()
	if err != nil {
		return err
	}
	return nil
}
