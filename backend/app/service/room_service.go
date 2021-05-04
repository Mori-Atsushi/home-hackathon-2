package service

import (
	"sync"

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
	mutex          *sync.Mutex
	channels       []chan<- domain.ChatRoomEvent
}

func NewRoomServiceImpl(
	userRepository repository.UserRepository,
) *RoomServiceImpl {
	return &RoomServiceImpl{
		userRepository: userRepository,
		mutex:          &sync.Mutex{},
		channels:       []chan<- domain.ChatRoomEvent{},
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
	s.addEventChannel(event)
	s.observeQueryChannel(user, query)
	return e
}

func (s *RoomServiceImpl) addEventChannel(eventChannel chan<- domain.ChatRoomEvent) {
	s.mutex.Lock()
	s.channels = append(s.channels, eventChannel)
	s.mutex.Unlock()
}

func (s *RoomServiceImpl) observeQueryChannel(user domain.User, queryChannel <-chan domain.ChatRoomQuery) {
	for {
		query, ok := <-queryChannel
		if !ok {
			break
		}
		s.handleQuery(user, query)
	}
}

func (s *RoomServiceImpl) handleQuery(user domain.User, query domain.ChatRoomQuery) {
	switch query.Type {
	case domain.ChatSend:
		chat := domain.NewChat(user, query.ChatSend.Message)
		event := domain.NewChatRecieveEvent(chat)
		s.sendEvent(event)
	}
}

func (s *RoomServiceImpl) sendEvent(event domain.ChatRoomEvent) {
	for _, channel := range s.channels {
		channel <- event
	}
}
