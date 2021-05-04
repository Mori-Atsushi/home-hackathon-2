package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ObserveUserCreatedUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : ObserveUserCreatedUseCase {
    override fun invoke(scope: CoroutineScope, input: Unit): StateFlow<Boolean> {
        return userRepository.isCreatedUser
    }
}
