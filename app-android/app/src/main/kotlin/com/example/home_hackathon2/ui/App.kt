package com.example.home_hackathon2.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.home_hackathon2.ui.initial.InitialScreen
import com.example.home_hackathon2.ui.tools.rememberViewModel

@Composable
fun App() {
    val appViewModel = rememberViewModel {
        it.getAppViewModel()
    }
    val isVisibleInitialScreen = appViewModel.isVisibleInitialScreen.collectAsState()
    if (isVisibleInitialScreen.value) {
        InitialScreen()
    } else {
        // TODO: change
        Text("Hello World")
    }
}
