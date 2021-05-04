package com.example.home_hackathon2.repository.helper

import com.example.home_hackathon2.pb.App
import com.example.home_hackathon2.source.Grpc
import com.example.home_hackathon2.util.ApplicationScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRoomHelper @Inject constructor(
    private val grpc: Grpc,
    private val authHelper: AuthHelper,
    private val applicationScope: ApplicationScope
) {
    private val request = MutableSharedFlow<App.ChatRoomEventRequest>(Channel.BUFFERED)
    private val _response = MutableSharedFlow<App.ChatRoomEventResponse>(Channel.BUFFERED)
    val response: Flow<App.ChatRoomEventResponse>
        get() = _response
    private var job: Job? = null

    fun join() {
        if (job != null) throw IllegalStateException("can not join while joined")
        val meta = authHelper.getMeta()
        job = applicationScope.launch {
            grpc.chatRoomEvent(request, meta).collect {
                _response.emit(it)
            }
        }
    }

    fun leave() {
        val job = job ?: throw IllegalStateException("can not leave when not joined")
        job.cancel()
        this.job = null
    }

    suspend fun send(request: App.ChatRoomEventRequest) {
        if (job == null) throw IllegalStateException("require call join")
        this.request.emit(request)
    }
}
