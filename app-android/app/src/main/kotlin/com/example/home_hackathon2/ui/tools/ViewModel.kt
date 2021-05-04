package com.example.home_hackathon2.ui.tools

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class ViewModel {
    private val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate
    val viewModelScope = CoroutineScope(coroutineContext)

    @CallSuper
    open fun onCleared() {
        coroutineContext.cancel()
    }
}
