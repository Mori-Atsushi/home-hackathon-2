package com.example.home_hackathon2.ui.chat

import com.example.home_hackathon2.model.ChatRoom
import com.example.home_hackathon2.ui.tools.ViewModel
import com.example.home_hackathon2.usecase.JoinRoomUseCase
import com.example.home_hackathon2.usecase.LeaveRoomUseCase
import com.example.home_hackathon2.usecase.ObserveChatRoomUseCase
import com.example.home_hackathon2.usecase.common.invoke
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatRoomViewModel @Inject constructor(
    private val joinRoomUseCase: JoinRoomUseCase,
    private val leaveRoomUseCase: LeaveRoomUseCase,
    observeChatRoomUseCase: ObserveChatRoomUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            joinRoomUseCase.invoke()
        }
    }

    val chatRoom: StateFlow<ChatRoom> = observeChatRoomUseCase.invoke(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Eagerly, ChatRoom())

    override fun onCleared() {
        super.onCleared()
        leaveRoomUseCase.invoke()
    }
}
