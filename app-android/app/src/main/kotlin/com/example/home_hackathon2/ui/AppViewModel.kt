package com.example.home_hackathon2.ui

import com.example.home_hackathon2.ui.tools.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AppViewModel @Inject constructor() : ViewModel() {
    val isVisibleInitialScreen: StateFlow<Boolean> = MutableStateFlow(true) // TODO: change
}
