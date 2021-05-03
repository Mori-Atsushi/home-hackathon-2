package com.example.home_hackathon2.repository.helper

import com.example.home_hackathon2.pb.App
import com.example.home_hackathon2.pb.AppServiceGrpcKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRoomHelper @Inject constructor(
    private val service: AppServiceGrpcKt.AppServiceCoroutineStub
) {
    private var requestChannel: SendChannel<App.ChatRoomEventRequest>? = null
    private var responseFlow: Flow<App.ChatRoomEventResponse>? = null
    val response: Flow<App.ChatRoomEventResponse>
        get() = responseFlow ?: throw IllegalStateException("require call join")

    fun join() {
        val channel = Channel<App.ChatRoomEventRequest>(Channel.BUFFERED)
        responseFlow = service.chatRoomEvent(channel.receiveAsFlow())
    }

    fun leave() {
        requestChannel?.close()
        requestChannel = null
        responseFlow = null
    }

    suspend fun send(request: App.ChatRoomEventRequest) {
        val requestChannel = this.requestChannel ?: throw IllegalStateException("require call join")
        requestChannel.send(request)
    }
}
