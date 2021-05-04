package com.example.home_hackathon2.repository

import com.example.home_hackathon2.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    val message: Flow<Chat>
    suspend fun sendMessage(message: String)
}
