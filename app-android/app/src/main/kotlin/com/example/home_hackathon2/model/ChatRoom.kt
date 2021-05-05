package com.example.home_hackathon2.model

data class ChatRoom(
    val items: List<ChatRoomItem> = emptyList(),
    val myPendingChat: String? = null
) {
    fun added(chat: Chat): ChatRoom {
        val isPendingChat = chat.isMe && myPendingChat != null && myPendingChat == chat.message
        return copy(
            items = items + ChatRoomItem.Chat(chat),
            myPendingChat = if (isPendingChat) null else myPendingChat
        )
    }

    fun addedPendingChat(message: String?): ChatRoom {
        return copy(
            myPendingChat = message
        )
    }

    fun isEmpty(): Boolean {
        return items.isEmpty() && myPendingChat == null
    }

    val lastIndex: Int
        get() = items.lastIndex + if (myPendingChat == null) 0 else 1
}
