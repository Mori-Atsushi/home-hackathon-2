package domain

import "sync"

type Room struct {
	mutex    *sync.Mutex
	sessions map[string]*Session
}

func NewRoom() *Room {
	return &Room{
		mutex:    &sync.Mutex{},
		sessions: map[string]*Session{},
	}
}

func (r *Room) Join(
	user User,
	event chan<- ChatRoomEvent,
) string {
	session := NewSession(event)
	r.mutex.Lock()
	r.sessions[session.ID] = session
	r.mutex.Unlock()
	return session.ID
}

func (r *Room) HandleQuery(user User, query ChatRoomQuery) {
	switch query.Type {
	case ChatSend:
		chat := NewChat(user, query.ChatSend.Message)
		event := NewChatRecieveEvent(chat)
		r.sendEvent(event)
	}
}

func (r *Room) Leave(sessionID string) {
	r.mutex.Lock()
	delete(r.sessions, sessionID)
	r.mutex.Unlock()
}

func (r *Room) sendEvent(event ChatRoomEvent) {
	for _, channel := range r.sessions {
		channel.Send(event)
	}
}
