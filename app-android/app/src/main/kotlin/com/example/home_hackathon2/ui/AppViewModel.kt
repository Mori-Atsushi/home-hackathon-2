package com.example.home_hackathon2.ui

import com.example.home_hackathon2.ui.tools.ViewModel
import com.example.home_hackathon2.usecase.ObserveUserCreatedUseCase
import com.example.home_hackathon2.usecase.common.invoke
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AppViewModel @Inject constructor(
    observeUserCreatedUseCase: ObserveUserCreatedUseCase
) : ViewModel() {
    val isVisibleInitialScreen: StateFlow<Boolean> =
        observeUserCreatedUseCase.invoke(viewModelScope)
}
