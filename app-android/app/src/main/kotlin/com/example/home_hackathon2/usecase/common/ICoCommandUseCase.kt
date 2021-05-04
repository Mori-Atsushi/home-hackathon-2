package com.example.home_hackathon2.usecase.common

import com.example.home_hackathon2.model.common.Result

interface ICoCommandUseCase<Input, Output : Result<*>> {
    suspend fun invoke(input: Input): Output
}

suspend fun <Output : Result<*>> ICoCommandUseCase<Unit, Output>.invoke(): Output {
    return invoke(Unit)
}
