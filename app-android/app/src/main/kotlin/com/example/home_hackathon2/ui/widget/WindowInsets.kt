package com.example.home_hackathon2.ui.widget

import androidx.core.graphics.Insets
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat

fun Modifier.paddingInsets(
    top: Boolean = false,
    start: Boolean = false,
    end: Boolean = false,
    bottom: Boolean = false
): Modifier = composed {
    val view = LocalView.current
    val state = remember {
        mutableStateOf(Insets.of(0, 0, 0, 0))
    }
    DisposableEffect(view) {
        val applyCallback = OnApplyWindowInsetsListener { _, insets ->
            val mask = WindowInsetsCompat.Type.systemBars()
            state.value = insets.getInsets(mask)
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, applyCallback)
        onDispose {
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }
    }
    LocalDensity.current.run {
        val insets = state.value
        padding(
            top = if (top) insets.top.toDp() else 0.dp,
            start = if (end) insets.right.toDp() else 0.dp,
            bottom = if (bottom) insets.bottom.toDp() else 0.dp,
            end = if (start) insets.left.toDp() else 0.dp
        )
    }
}

fun Modifier.paddingBottomIme(): Modifier = composed {
    val view = LocalView.current
    val density = LocalDensity.current
    val height = remember {
        mutableStateOf(0.dp)
    }
    DisposableEffect(view) {
        val animCallback = object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {
            override fun onProgress(
                insets: WindowInsetsCompat,
                runningAnimations: MutableList<WindowInsetsAnimationCompat>
            ): WindowInsetsCompat {
                height.value = insets.bottomImeInsetDp(density)
                return insets
            }
        }
        val applyCallback = OnApplyWindowInsetsListener { _, insets ->
            height.value = insets.bottomImeInsetDp(density)
            insets
        }
        ViewCompat.setWindowInsetsAnimationCallback(view, animCallback)
        ViewCompat.setOnApplyWindowInsetsListener(view, applyCallback)
        onDispose {
            ViewCompat.setWindowInsetsAnimationCallback(view, null)
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }
    }
    padding(bottom = height.value)
}

private fun WindowInsetsCompat.bottomImeInsetDp(density: Density): Dp {
    val mask = WindowInsetsCompat.Type.ime() or WindowInsetsCompat.Type.systemBars()
    val bottom = getInsets(mask).bottom
    return density.run {
        bottom.toDp()
    }
}
