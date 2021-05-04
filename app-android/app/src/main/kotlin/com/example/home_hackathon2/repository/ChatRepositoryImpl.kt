package com.example.home_hackathon2.repository

import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.pb.App
import com.example.home_hackathon2.repository.helper.ChatRoomHelper
import com.example.home_hackathon2.repository.mapper.toModel
import com.example.home_hackathon2.source.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatRoomHelper: ChatRoomHelper,
    private val preferences: Preferences
) : ChatRepository {
    override val message: Flow<Chat>
        get() = chatRoomHelper.response
            .filter { it.hasChatRecieveResponse() }
            .map { toModel(it.chatRecieveResponse) }

    override suspend fun sendMessage(message: String) {
        val sendChatRequest = App.SendChatRequest
            .newBuilder()
            .setMessage(message)
            .build()
        val eventRequest = App.ChatRoomEventRequest.newBuilder()
            .setSendChatRequest(sendChatRequest)
            .build()
        chatRoomHelper.send(eventRequest)
    }

    private fun toModel(response: App.ChatReceiveResponse): Chat {
        val myUserId = requireNotNull(preferences.userId) {
            "require userId"
        }
        return response.chat.toModel(myUserId)
    }
}
