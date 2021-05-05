package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.background
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.model.ChatRoom
import com.example.home_hackathon2.ui.res.*
import com.example.home_hackathon2.ui.tools.rememberViewModel
import com.example.home_hackathon2.ui.widget.paddingInsets

@Composable
fun ChatRoomScreen() {
    val viewModel = rememberViewModel {
        it.getChatRoomViewModel()
    }
    val chatRoom = viewModel.chatRoom.collectAsState()
    Box(
        modifier = Modifier
            .paddingInsets(top = true, bottom = true)
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Header()
            MainContent(
                chatRoom = chatRoom.value,
                modifier = Modifier.weight(1F)
            )
            RoomFooter()
        }
    }
}

@Composable
private fun MainContent(
    chatRoom: ChatRoom,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = chatRoom.isEmpty()
    ) {
        if (it) {
            Empty()
        } else {
            ChatList(
                data = chatRoom,
                contentPadding = PaddingValues(top = 12.dp, bottom = 30.dp)
            )
        }
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

@Preview
@Composable
private fun Empty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .width(100.dp)
                .padding(bottom = 20.dp),
            painter = painterResource(id = R.drawable.icon),
            contentDescription = null,
            tint = COLOR_PRIMARY
        )
        Text(
            text = "まだ誰も話してないようです\n喋るだけでメッセージが送信されます",
            color = COLOR_DARK,
            textAlign = TextAlign.Center
        )
    }
}

