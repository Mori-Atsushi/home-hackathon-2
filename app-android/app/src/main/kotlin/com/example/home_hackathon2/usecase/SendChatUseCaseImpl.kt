package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.model.common.SimpleResult
import com.example.home_hackathon2.repository.ChatRepository
import javax.inject.Inject

class SendChatUseCaseImpl @Inject constructor(
    private val chatRepository: ChatRepository
) : SendChatUseCase {
    override suspend fun invoke(input: String): SimpleResult<Unit> {
        return SimpleResult.of {
            chatRepository.sendMessage(input)
        }
    }
}
