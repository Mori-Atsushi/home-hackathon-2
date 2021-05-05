package com.example.home_hackathon2.ui.chat

import android.content.Intent
import android.speech.RecognizerIntent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.ui.listener.SimpleRecognizerListener
import com.example.home_hackathon2.ui.res.COLOR_DARK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.res.COLOR_PRIMARY
import com.example.home_hackathon2.ui.res.COLOR_WHITE
import com.example.home_hackathon2.ui.tools.rememberViewModel

@Composable
fun RoomFooter() {
    val viewModel = rememberViewModel {
        it.getRoomFooterViewModel()
    }
    val volume = viewModel.volume.collectAsState()
    val sizeDp = remember(volume.value) {
        lerp(60.dp, 70.dp, volume.value)
    }
    val innerSize = animateDpAsState(targetValue = sizeDp)
    val outerSize = remember(innerSize.value) {
        innerSize.value + 2.dp
    }
    Box(
        modifier = Modifier.height(56.dp)
    ) {
        Box(
            modifier = Modifier
                .offset(y = (-28).dp)
                .requiredWidth(outerSize)
                .requiredHeight(outerSize)
                .clip(CircleShape)
                .background(COLOR_LIGHT)
                .align(Alignment.Center)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(1.dp)
                .background(COLOR_LIGHT)
        )
        MicButton(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-28).dp)
                .requiredWidth(innerSize.value)
                .requiredHeight(innerSize.value),
            onClick = viewModel::switchIsMute,
            isMuted = viewModel.isMuted.collectAsState().value
        )
    }
    SpeechRecognizer(
        viewModel = viewModel
    )
}

@Composable
private fun MicButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isMuted: Boolean = false
) {
    val color = if (isMuted) {
        COLOR_DARK
    } else {
        COLOR_PRIMARY
    }
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(COLOR_WHITE)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick
            )
            .border(2.dp, color, CircleShape)
    ) {
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .width(52.dp)
                .height(52.dp)
                .background(color = color)
                .padding(10.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_microphone_solid),
            contentDescription = null,
            tint = COLOR_WHITE
        )
    }
}

@Composable
private fun SpeechRecognizer(
    viewModel: RoomFooterViewModel
) {
    val context = LocalContext.current
    val speechRecognizer = remember {
        android.speech.SpeechRecognizer.createSpeechRecognizer(context)
    }
    val intent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).also {
            it.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            it.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            it.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }
    }
    val callback = remember {
        object : SimpleRecognizerListener.SimpleRecognizerResponseListener {
            override fun onRecognizedResult(speechText: String) {
                viewModel.endSpeech(speechText)
                speechRecognizer.cancel()
                if (!viewModel.isMuted.value) {
                    speechRecognizer.startListening(intent)
                }
            }

            override fun onPartialResults(speechText: String) {
                viewModel.partialSpeech(speechText)
            }

            override fun onBeginningOfSpeech() {
                viewModel.startSpeech()
            }

            override fun onRmsChanged(rmsdB: Float) {
                viewModel.changeRms(rmsdB)
            }
        }
    }
    val isMuted = viewModel.isMuted.collectAsState().value

    DisposableEffect(isMuted) {
        if (isMuted) {
            speechRecognizer.stopListening()
        } else {
            speechRecognizer.setRecognitionListener(SimpleRecognizerListener(callback))
            speechRecognizer.startListening(intent)
        }
        onDispose {
            if (!isMuted) {
                viewModel.cancelSpeech()
                speechRecognizer.stopListening()
            }
        }
    }
}
