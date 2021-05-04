package com.example.home_hackathon2.ui.tools

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.home_hackathon2.di.ViewModelEntryPoint
import dagger.hilt.EntryPoints

private val LocalViewModelEntryPoint = compositionLocalOf<ViewModelEntryPoint> {
    error("ViewModelEntryPoint not found")
}

@Composable
fun <T : ViewModel> rememberViewModel(
    calculation: @DisallowComposableCalls (entryPoint: ViewModelEntryPoint) -> T
): T {
    val viewModelEntryPoint = LocalViewModelEntryPoint.current
    val viewModel = remember(viewModelEntryPoint) {
        calculation(viewModelEntryPoint)
    }
    DisposableEffect(viewModel) {
        onDispose {
            viewModel.onCleared()
        }
    }
    return viewModel
}

@Composable
fun ViewModelEntryPointProvider(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val viewModelEntryPoint = remember(context) {
        EntryPoints.get(context, ViewModelEntryPoint::class.java)
    }
    CompositionLocalProvider(LocalViewModelEntryPoint provides viewModelEntryPoint) {
        content()
    }
}
