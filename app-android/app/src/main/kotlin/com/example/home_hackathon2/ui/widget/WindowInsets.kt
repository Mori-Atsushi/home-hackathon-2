package com.example.home_hackathon2.ui.widget

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

fun Modifier.paddingTopStatusBar(): Modifier = composed {
    val view = LocalView.current
    val density = LocalDensity.current
    val height = remember {
        mutableStateOf(0.dp)
    }
    DisposableEffect(view) {
        val applyCallback = OnApplyWindowInsetsListener { _, insets ->
            val mask = WindowInsetsCompat.Type.statusBars()
            val bottom = insets.getInsets(mask).top
            height.value = density.run {
                bottom.toDp()
            }
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(view, applyCallback)
        onDispose {
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }
    }
    padding(top = height.value)
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
