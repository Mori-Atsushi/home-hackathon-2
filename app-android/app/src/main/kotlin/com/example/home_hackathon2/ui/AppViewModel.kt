package com.example.home_hackathon2.ui

import com.example.home_hackathon2.ui.tools.ViewModel
import com.example.home_hackathon2.usecase.ObserveUserCreatedUseCase
import com.example.home_hackathon2.usecase.common.invoke
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AppViewModel @Inject constructor(
    observeUserCreatedUseCase: ObserveUserCreatedUseCase
) : ViewModel() {
    val isVisibleInitialScreen: StateFlow<Boolean> =
        observeUserCreatedUseCase.invoke(viewModelScope)
            .map { !it }
            .stateIn(viewModelScope, SharingStarted.Eagerly, true)
}
