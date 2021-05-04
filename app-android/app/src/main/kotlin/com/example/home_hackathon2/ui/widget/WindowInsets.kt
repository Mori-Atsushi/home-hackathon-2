package com.example.home_hackathon2.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat

fun Modifier.paddingBottomIme(): Modifier = composed {
    val view = LocalView.current
    val density = LocalDensity.current
    val height = remember {
        mutableStateOf(0.dp)
    }
    DisposableEffect(view) {
        val callback = object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {
            override fun onProgress(
                insets: WindowInsetsCompat,
                runningAnimations: MutableList<WindowInsetsAnimationCompat>
            ): WindowInsetsCompat {
                val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                height.value = density.run {
                    bottom.toDp()
                }
                return insets
            }
        }
        ViewCompat.setWindowInsetsAnimationCallback(view, callback)
        onDispose {
            ViewCompat.setWindowInsetsAnimationCallback(view, null)
        }
    }
    padding(bottom = height.value)
}
