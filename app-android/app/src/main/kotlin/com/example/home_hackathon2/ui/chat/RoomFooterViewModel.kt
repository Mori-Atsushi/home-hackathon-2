package com.example.home_hackathon2.ui.chat

import com.example.home_hackathon2.ui.tools.ViewModel
import com.example.home_hackathon2.usecase.SendChatUseCase
import com.example.home_hackathon2.usecase.SendPendingChatUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomFooterViewModel @Inject constructor(
    private val sendChatUseCase: SendChatUseCase,
    private val sendPendingChatUseCase: SendPendingChatUseCase
) : ViewModel() {
    companion object {
        const val MAX_RMS_DB = 10F
    }

    private val _volume = MutableStateFlow(0F)
    val volume: StateFlow<Float> = _volume

    fun startSpeech() {
        viewModelScope.launch {
            val input = SendPendingChatUseCase.Input.Start
            sendPendingChatUseCase.invoke(input)
        }
    }

    fun partialSpeech(message: String) {
        if (message.isEmpty()) {
            return
        }
        viewModelScope.launch {
            val input = SendPendingChatUseCase.Input.Message(message)
            sendPendingChatUseCase.invoke(input)
        }
    }

    fun cancelSpeech() {
        viewModelScope.launch {
            val input = SendPendingChatUseCase.Input.Cancel
            sendPendingChatUseCase.invoke(input)
        }
    }

    fun endSpeech(message: String) {
        if (message.isEmpty()) {
            cancelSpeech()
            return
        }
        viewModelScope.launch {
            sendChatUseCase.invoke(message)
        }
    }

    fun changeRms(rmsdB: Float) {
        _volume.value = when {
            rmsdB < 0F -> 0F
            rmsdB < MAX_RMS_DB -> rmsdB
            else -> MAX_RMS_DB
        } / MAX_RMS_DB
    }
}
