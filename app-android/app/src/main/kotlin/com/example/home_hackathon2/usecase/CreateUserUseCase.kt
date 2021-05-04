package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.model.common.ErrorResult
import com.example.home_hackathon2.model.common.Result
import com.example.home_hackathon2.model.common.UnitResult
import com.example.home_hackathon2.usecase.common.ICoCommandUseCase

interface CreateUserUseCase : ICoCommandUseCase<CreateUserUseCase.Input, CreateUserUseCase.Output> {
    data class Input(
        val name: String
    )

    sealed class Output : Result<Unit> {
        object Success : Output(), UnitResult

        object EmptyNameError : Output(), ErrorResult<Unit>

        data class OtherError(
            override val exceptionOrNull: Throwable
        ) : Output(), ErrorResult<Unit>
    }
}
