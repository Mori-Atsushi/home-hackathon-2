package com.example.home_hackathon2.repository

import com.example.home_hackathon2.repository.helper.ChatRoomHelper
import javax.inject.Inject

class ChatRoomRepositoryImpl @Inject constructor(
    private val chatRoomHelper: ChatRoomHelper
) : ChatRoomRepository {
    override fun join() {
        chatRoomHelper.join()
    }

    override fun leave() {
        chatRoomHelper.leave()
    }
}
