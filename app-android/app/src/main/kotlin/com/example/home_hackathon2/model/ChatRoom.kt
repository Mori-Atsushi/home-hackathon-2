package com.example.home_hackathon2.model

data class ChatRoom(
    val items: List<ChatRoomItem> = emptyList()
) {
    fun added(chat: Chat): ChatRoom {
        return copy(
            items = items + ChatRoomItem.Chat(chat)
        )
    }
}
