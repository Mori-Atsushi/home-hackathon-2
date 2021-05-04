package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.res.COLOR_PRIMARY
import com.example.home_hackathon2.ui.res.COLOR_WHITE
import com.example.home_hackathon2.ui.tools.rememberViewModel
import com.example.home_hackathon2.ui.widget.paddingTopStatusBar

@Composable
fun ChatRoomScreen() {
    val viewModel = rememberViewModel {
        it.getChatRoomViewModel()
    }
    val chatRoom = viewModel.chatRoom.collectAsState()
    Box(
        modifier = Modifier
            .paddingTopStatusBar()
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Header()
            ChatList(
                items = chatRoom.value.items,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }
        MicButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        )
    }
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


@Composable
private fun MicButton(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        Surface(
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            elevation = 4.dp
        ) {
            Button(
                modifier = Modifier.fillMaxSize(),
                onClick = { /* TODO */ },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = COLOR_PRIMARY,
                    contentColor = COLOR_WHITE
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_microphone_solid),
                    contentDescription = null
                )
            }
        }
    }
}
