package com.example.home_hackathon2.ui.chat

import com.example.home_hackathon2.ui.tools.ViewModel
import com.example.home_hackathon2.usecase.SendChatUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MicInViewModel @Inject constructor(
    private val sendChatUseCase: SendChatUseCase
) : ViewModel() {
    fun send(message: String) {
        viewModelScope.launch {
            sendChatUseCase.invoke(message)
        }
    }
}
