package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.ui.listener.SimpleRecognizerListener
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.res.COLOR_PRIMARY
import com.example.home_hackathon2.ui.res.COLOR_WHITE
import com.example.home_hackathon2.ui.tools.rememberViewModel
import com.example.home_hackathon2.ui.widget.paddingInsets

@Composable
fun ChatRoomScreen() {
    val chatRoomViewModel = rememberViewModel {
        it.getChatRoomViewModel()
    }
    val micInViewModel = rememberViewModel {
        it.getMicInViewModel()
    }
    val chatRoom = chatRoomViewModel.chatRoom.collectAsState()
    Box(
        modifier = Modifier
            .paddingInsets(top = true, bottom = true)
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Header()
            ChatList(
                items = chatRoom.value.items,
                modifier = Modifier.weight(1F),
                contentPadding = PaddingValues(top = 12.dp, bottom = 30.dp)
            )
            Footer()
        }
    }
    SpeechRecognizer(
        onRecognized = {
            micInViewModel.send(it)
        }
    )
}

@Composable
private fun Header() {
    Box(
        modifier = Modifier.height(56.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterStart),
            color = COLOR_BLACK,
            fontSize = 20.sp,
            text = "チャットルーム"
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(1.dp)
                .background(COLOR_LIGHT)
        )
    }
}

@Preview
@Composable
private fun Footer() {
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
fun SpeechRecognizer(
    onRecognized: (String) -> Unit
) {
    val context = LocalContext.current
    val speechRecognizer = remember {
        SpeechRecognizer.createSpeechRecognizer(context)
    }
    val intent = remember {
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
    }
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, LocalContext.current.packageName)

    val callback = remember{
        object : SimpleRecognizerListener.SimpleRecognizerResponseListener {
            override fun onRecognizedResult(speechText: String) {
                if (speechText.isNotBlank()) {
                    onRecognized(speechText)
                }
                speechRecognizer.stopListening()
                speechRecognizer.startListening(intent)
            }
        }
    }

    DisposableEffect(true) {
        speechRecognizer.setRecognitionListener(SimpleRecognizerListener(callback))
        speechRecognizer.startListening(intent)
        onDispose {
            speechRecognizer.stopListening()
        }
    }
}

