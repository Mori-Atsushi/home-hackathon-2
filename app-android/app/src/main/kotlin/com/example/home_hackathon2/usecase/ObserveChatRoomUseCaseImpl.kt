package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.model.ChatRoom
import com.example.home_hackathon2.repository.ChatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ObserveChatRoomUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
) : ObserveChatRoomUseCase {
    override fun invoke(scope: CoroutineScope, input: Unit): Flow<ChatRoom> {
        val flow = MutableStateFlow(ChatRoom())
        scope.launch {
            chatRepository.message.collect {
                flow.value = flow.value.added(it)
            }
        }
        return flow
    }
}
