package com.example.home_hackathon2.ui.chat

import android.content.Intent
import android.speech.RecognizerIntent
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.ui.listener.SimpleRecognizerListener
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.res.COLOR_PRIMARY
import com.example.home_hackathon2.ui.res.COLOR_WHITE
import com.example.home_hackathon2.ui.tools.rememberViewModel

@Preview
@Composable
fun RoomFooter() {
    val viewModel = rememberViewModel {
        it.getRoomFooterViewModel()
    }
    Box(
        modifier = Modifier.height(56.dp)
    ) {
        Box(
            modifier = Modifier
                .offset(y = (-30).dp)
                .requiredWidth(62.dp)
                .requiredHeight(62.dp)
                .clip(CircleShape)
                .background(COLOR_LIGHT)
                .align(Alignment.TopCenter)
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
                .offset(y = (-30).dp)
                .requiredWidth(60.dp)
                .requiredHeight(60.dp)
                .align(Alignment.TopCenter)
        )
    }
    SpeechRecognizer(
        viewModel = viewModel
    )
}

@Preview
@Composable
private fun MicButton(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .width(60.dp)
            .height(60.dp)
            .background(COLOR_WHITE)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = {}
            )
            .border(2.dp, COLOR_PRIMARY, CircleShape)
    ) {
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .width(52.dp)
                .height(52.dp)
                .background(color = COLOR_PRIMARY)
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
                speechRecognizer.startListening(intent)
            }

            override fun onPartialResults(speechText: String) {
                viewModel.partialSpeech(speechText)
            }

            override fun onBeginningOfSpeech() {
                viewModel.startSpeech()
            }
        }
    }

    DisposableEffect(true) {
        speechRecognizer.setRecognitionListener(SimpleRecognizerListener(callback))
        speechRecognizer.startListening(intent)
        onDispose {
            viewModel.cancelSpeech()
            speechRecognizer.stopListening()
        }
    }
}
