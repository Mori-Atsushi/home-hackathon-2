package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : CreateUserUseCase {
    override suspend fun invoke(
        input: CreateUserUseCase.Input
    ): CreateUserUseCase.Output {
        if (input.name.isEmpty()) {
            return CreateUserUseCase.Output.EmptyNameError
        }
        return try {
            userRepository.createUser(input.name)
            CreateUserUseCase.Output.Success
        } catch (e: Throwable) {
            CreateUserUseCase.Output.OtherError(e)
        }
    }
}
