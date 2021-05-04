package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.model.ChatRoomItem
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.res.COLOR_PRIMARY
import com.example.home_hackathon2.ui.res.COLOR_WHITE
import com.example.home_hackathon2.ui.tools.rememberViewModel

@Composable
fun ChatRoomScreen() {
    val viewModel = rememberViewModel {
        it.getChatRoomViewModel()
    }
    val chatRoom = viewModel.chatRoom.collectAsState()
    Box {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Header()
            ChatList(items = chatRoom.value.items)
        }
        MicButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        )
    }


}

@Composable
private fun Header(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(top = 56.dp)
    ) {
        Text(
            modifier = Modifier.padding(all = 24.dp),
            color = COLOR_BLACK,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = "チャットルーム"
        )
        TabRowDefaults.Divider(
            color = COLOR_LIGHT
        )
    }
}

@Composable
fun ChatList(items: List<ChatRoomItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(items) {
            when (it) {
                is ChatRoomItem.Chat -> {
                    if (it.value.user.name == "atushi") {
                        ChatRowMe(chat = it.value)
                    } else {
                        ChatRow(chat = it.value)
                    }
                }
            }
        }
    }
}

@Composable
fun ChatText(
    message: String,
    color: Color = COLOR_BLACK
) {
    Text(
        modifier = Modifier
            .widthIn(max = 285.dp)
            .padding(all = 8.dp),
        text = message,
        fontSize = 20.sp,
        color = color
    )
}

@Composable
fun ChatRowMe(
    chat: Chat,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Column(
            modifier = modifier.padding(
                start = 16.dp,
                top = 18.dp,
                end = 16.dp,
                bottom = 18.dp
            ),
            horizontalAlignment = Alignment.End
        ) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = COLOR_PRIMARY
            ) {
                ChatText(
                    message = chat.message,
                    color = COLOR_WHITE
                )
            }
            Text(text = "自分")
        }
    }

}

@Composable
fun ChatRow(chat: Chat) {
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            top = 18.dp,
            end = 16.dp,
            bottom = 18.dp
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium, // TODO: Fix chat shape
            color = COLOR_LIGHT
        ) {
            ChatText(message = chat.message)
        }
        Text(text = chat.user.name)
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
