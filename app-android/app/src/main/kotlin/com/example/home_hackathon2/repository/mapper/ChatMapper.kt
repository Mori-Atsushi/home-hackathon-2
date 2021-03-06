package com.example.home_hackathon2.repository.mapper

import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.pb.App

fun App.Chat.toModel(myUserId: String): Chat {
    return Chat(
        message = message,
        user = user.toModel(),
        isMe = user.id == myUserId
    )
}
