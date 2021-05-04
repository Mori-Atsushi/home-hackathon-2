package com.example.home_hackathon2.usecase.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface IObserveUseCase<Input, Output> {
    fun invoke(scope: CoroutineScope, input: Input): Flow<Output>
}

fun <Output> IObserveUseCase<Unit, Output>.invoke(scope: CoroutineScope): Flow<Output> {
    return invoke(scope, Unit)
}
