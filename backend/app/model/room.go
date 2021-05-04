package model

import (
	"sync"

	pb "local.packages/gen"
)

type Room struct {
	mutex    *sync.Mutex
	channels map[string]*Channel
}

func NewRoom() Room {
	return Room{
		mutex:    &sync.Mutex{},
		channels: map[string]*Channel{},
	}
}

func (r Room) SendChatEvent(chat *pb.Chat) {
	event := NewChatEvent(chat)
	r.mutex.Lock()
	r.sendEvent(event)
	r.mutex.Unlock()
}

func (r Room) sendEvent(event Event) {
	for _, channel := range r.channels {
		channel.ChatEvent(event)
	}
}

func (r Room) ReceiveEvent(chat pb.Chat) <-chan Event {
	r.mutex.Lock()
	channel := r.channels[chat.GetUser().GetId()]
	r.mutex.Unlock()
	return channel.channel
}
