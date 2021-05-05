package com.example.home_hackathon2.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.example.home_hackathon2.ui.tools.ViewModelEntryPointProvider
import com.example.home_hackathon2.ui.widget.LocalWindow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CompositionLocalProvider(
                LocalWindow provides window
            ) {
                ViewModelEntryPointProvider {
                    App()
                }
            }
        }
    }
}
