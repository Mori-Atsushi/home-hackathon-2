package com.example.home_hackathon2.usecase.common

import com.example.home_hackathon2.model.common.Result

interface ICommandUseCase<Input, Output : Result<*>> {
    fun invoke(input: Input): Output
}

fun <Output : Result<*>> ICommandUseCase<Unit, Output>.invoke(): Output {
    return invoke(Unit)
}
