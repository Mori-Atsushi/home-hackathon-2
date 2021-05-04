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
	room           *domain.Room
}

func NewRoomServiceImpl(
	userRepository repository.UserRepository,
) *RoomServiceImpl {
	return &RoomServiceImpl{
		userRepository: userRepository,
		room:           domain.NewRoom(),
	}
}

func (s *RoomServiceImpl) Join(
	auth domain.Auth,
	query <-chan domain.ChatRoomQuery,
	event chan<- domain.ChatRoomEvent,
) error {
	user, e := s.userRepository.Get(auth)
	if e != nil {
		return e
	}
	sessionId := s.room.Join(user, event)
	s.observeQueryChannel(user, query)
	s.room.Leave(sessionId)
	return e
}

func (s *RoomServiceImpl) observeQueryChannel(user domain.User, queryChannel <-chan domain.ChatRoomQuery) {
	for {
		query, ok := <-queryChannel
		if !ok {
			break
		}
		s.room.HandleQuery(user, query)
	}
}
