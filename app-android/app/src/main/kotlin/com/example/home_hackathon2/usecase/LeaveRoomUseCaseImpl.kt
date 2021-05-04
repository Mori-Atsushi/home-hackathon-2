package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.model.common.SimpleResult
import com.example.home_hackathon2.repository.ChatRoomRepository
import javax.inject.Inject

class LeaveRoomUseCaseImpl @Inject constructor(
    private val repository: ChatRoomRepository
) : LeaveRoomUseCase {
    override fun invoke(input: Unit): SimpleResult<Unit> {
        return SimpleResult.of {
            repository.leave()
        }
    }
}
