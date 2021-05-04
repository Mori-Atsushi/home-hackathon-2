package domain

import "github.com/google/uuid"

type Session struct {
	ID      string
	channel chan<- ChatRoomEvent
}

func NewSession(channel chan<- ChatRoomEvent) *Session {
	return &Session{
		ID:      uuid.Must(uuid.NewRandom()).String(),
		channel: channel,
	}
}

func (s *Session) Send(event ChatRoomEvent) {
	s.channel <- event
}
