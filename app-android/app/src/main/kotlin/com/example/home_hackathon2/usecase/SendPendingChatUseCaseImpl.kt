package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.model.common.SimpleResult
import com.example.home_hackathon2.repository.ChatRepository
import javax.inject.Inject

class SendPendingChatUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
) : SendPendingChatUseCase {
    override suspend fun invoke(input: SendPendingChatUseCase.Input): SimpleResult<Unit> {
        return SimpleResult.of {
            val value = when (input) {
                SendPendingChatUseCase.Input.Cancel -> null
                is SendPendingChatUseCase.Input.Message -> input.value
                SendPendingChatUseCase.Input.Start -> "......"
            }
            chatRepository.setPendingMessage(value)
        }
    }
}
