package com.example.home_hackathon2.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.home_hackathon2.ui.chat.ChatRoomScreen
import com.example.home_hackathon2.ui.initial.InitialScreen
import com.example.home_hackathon2.ui.tools.rememberViewModel

@Composable
fun App() {
    val appViewModel = rememberViewModel {
        it.getAppViewModel()
    }
    val isVisibleInitialScreen = appViewModel.isVisibleInitialScreen.collectAsState()
    Crossfade(targetState = isVisibleInitialScreen.value) {
        if (it) {
            InitialScreen()
        } else {
            ChatRoomScreen()
        }
    }
}
