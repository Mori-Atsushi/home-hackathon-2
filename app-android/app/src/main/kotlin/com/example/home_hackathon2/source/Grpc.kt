package com.example.home_hackathon2.source

import com.example.home_hackathon2.pb.App
import io.grpc.Metadata
import kotlinx.coroutines.flow.Flow

interface Grpc {
    suspend fun createUser(
        request: App.CreateUserRequest,
        meta: Metadata = Metadata()
    ): App.CreateUserResponse

    fun chatRoomEvent(
        requests: Flow<App.ChatRoomEventRequest>,
        meta: Metadata = Metadata()
    ): Flow<App.ChatRoomEventResponse>
}
