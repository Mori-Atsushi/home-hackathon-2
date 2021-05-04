package service

import (
	"com.home-hackathon-2/backend/domain"
	"com.home-hackathon-2/backend/repository"
)

type RoomService interface {
	Join(
		auth domain.Auth,
		query <-chan domain.ChatRoomQuery,
		event chan<- domain.ChatRoomEvent,
	) error
}

type RoomServiceImpl struct {
	userRepository repository.UserRepository
}

func NewRoomServiceImpl(
	userRepository repository.UserRepository,
) *RoomServiceImpl {
	return &RoomServiceImpl{
		userRepository: userRepository,
	}
}

func (s *RoomServiceImpl) Join(
	auth domain.Auth,
	query <-chan domain.ChatRoomQuery,
	event chan<- domain.ChatRoomEvent,
) error {
	_, e := s.userRepository.Get(auth)
	return e
}
