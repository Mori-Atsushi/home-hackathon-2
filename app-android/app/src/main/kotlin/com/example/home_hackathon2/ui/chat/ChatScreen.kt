package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.R
import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.model.User
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.res.COLOR_PRIMARY
import com.example.home_hackathon2.ui.res.COLOR_WHITE

@Composable
fun ChatScreen() {
    val dummy = listOf(
        Chat(
            user = User(
                id = "hoge",
                name = "atushi"
            ),
            message = "こんにちは"
        ),
        Chat(
            user = User(
                id = "hoge",
                name = "chigichan24"
            ),
            message = "こんにちは、今日はいい天気ですね"
        ),
        Chat(
            user = User(
                id = "hoge",
                name = "chigichan24"
            ),
            message = "何していますか？"
        ),
        Chat(
            user = User(
                id = "hoge",
                name = "atushi"
            ),
            message = "今日はおうちハッカソンでチャットアプリを作っています"
        ),
        Chat(
            user = User(
                id = "hoge",
                name = "euglena1215"
            ),
            message = "こんにちは"
        )
    )
    Box {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Header()
            ChatList(chats = dummy)
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
fun ChatList(chats: List<Chat>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        chats.forEach {
            if (it.user.name == "atushi") ChatRowMe(chat = it, modifier = Modifier.align(Alignment.End)) else ChatRow(chat = it)
        }
    }
}

@Composable
fun ChatRowMe(
    chat: Chat,
    modifier: Modifier = Modifier
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
            Text(
                modifier = Modifier
                    .widthIn(max = 285.dp)
                    .padding(all = 8.dp),
                text = chat.message,
                fontSize = 20.sp,
                color = COLOR_WHITE
            )
        }
        Text(text = "自分")
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
            shape = MaterialTheme.shapes.medium,
            color = COLOR_LIGHT
        ) {
            Text(
                modifier = Modifier
                    .widthIn(max = 285.dp)
                    .padding(all = 8.dp),
                text = chat.message,
                fontSize = 20.sp,
            )
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
                onClick = { /* Do something! */ },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = COLOR_PRIMARY,
                    contentColor = COLOR_WHITE
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_microphone_solid),
                    contentDescription = null // decorative element
                )
            }
        }

    }
}
