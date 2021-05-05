package com.example.home_hackathon2.usecase

import com.example.home_hackathon2.model.common.SimpleResult
import com.example.home_hackathon2.usecase.common.ICoCommandUseCase

interface SendPendingChatUseCase :
    ICoCommandUseCase<SendPendingChatUseCase.Input, SimpleResult<Unit>> {
    sealed class Input {
        object Start : Input()
        data class Message(val value: String) : Input()
        object Cancel : Input()
    }
}
