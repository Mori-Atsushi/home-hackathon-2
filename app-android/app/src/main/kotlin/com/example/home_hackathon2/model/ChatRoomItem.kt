package com.example.home_hackathon2.model

import com.example.home_hackathon2.model.Chat as ChatModel

sealed class ChatRoomItem {
    data class Chat(
        val value: ChatModel
    ) : ChatRoomItem()
}
