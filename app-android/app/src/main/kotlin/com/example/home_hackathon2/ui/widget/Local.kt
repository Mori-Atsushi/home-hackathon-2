package com.example.home_hackathon2.ui.widget

import android.view.Window
import androidx.compose.runtime.compositionLocalOf

val LocalWindow = compositionLocalOf<Window> {
    error("not found window")
}
