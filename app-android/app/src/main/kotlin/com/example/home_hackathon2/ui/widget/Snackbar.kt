package com.example.home_hackathon2.ui.widget

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.home_hackathon2.ui.res.COLOR_ERROR
import com.example.home_hackathon2.ui.res.COLOR_WHITE
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun ErrorSnackbarHost(
    messages: Flow<String>,
    modifier: Modifier = Modifier
) {
    val hostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(messages) {
        var job: Job? = null
        messages.collect {
            job?.cancel()
            job = launch {
                hostState.showSnackbar(
                    message = it,
                    actionLabel = "OK"
                )
            }
        }
    }
    SnackbarHost(
        modifier = modifier,
        hostState = hostState
    ) {
        Snackbar(
            snackbarData = it,
            backgroundColor = COLOR_ERROR,
            contentColor = COLOR_WHITE,
            actionColor = COLOR_WHITE
        )
    }
}
