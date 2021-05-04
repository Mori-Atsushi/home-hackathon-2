package domain

import "sync"

type Room struct {
	mutex    *sync.Mutex
	channels map[Session]chan<- ChatRoomEvent
}

func NewRoom() *Room {
	return &Room{
		mutex:    &sync.Mutex{},
		channels: map[Session]chan<- ChatRoomEvent{},
	}
}

func (r *Room) Join(
	user User,
	event chan<- ChatRoomEvent,
) Session {
	session := NewSession()
	r.mutex.Lock()
	r.channels[session] = event
	r.mutex.Unlock()
	return session
}

func (r *Room) HandleQuery(user User, query ChatRoomQuery) {
	switch query.Type {
	case ChatSend:
		chat := NewChat(user, query.ChatSend.Message)
		event := NewChatRecieveEvent(chat)
		r.sendEvent(event)
	}
}

func (r *Room) Leave(session Session) {
	r.mutex.Lock()
	delete(r.channels, session)
	r.mutex.Unlock()
}

func (r *Room) sendEvent(event ChatRoomEvent) {
	for _, channel := range r.channels {
		channel <- event
	}
}
