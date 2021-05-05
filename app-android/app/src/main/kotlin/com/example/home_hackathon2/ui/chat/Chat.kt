package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.model.User
import com.example.home_hackathon2.ui.res.*

@Preview
@Composable
private fun Chat() {
    val user = User(
        id = "id",
        name = "chigichan24"
    )
    val chat = Chat(
        user = user,
        message = "message",
        isMe = true
    )
    Chat(chat = chat, isBottomSame = true)
}

@Composable
fun Chat(myPartialMessage: String) {
    Chat(
        isMe = true,
        name = "自分",
        message = myPartialMessage,
        alpha = 0.5F
    )
}

@Composable
fun Chat(
    chat: Chat,
    isBottomSame: Boolean = false
) {
    val name = if (chat.isMe) {
        "自分"
    } else {
        chat.user.name
    }
    Chat(
        isMe = chat.isMe,
        name = name,
        message = chat.message,
        isBottomSame = isBottomSame
    )
}


@Composable
private fun Chat(
    isMe: Boolean,
    name: String,
    message: String,
    isBottomSame: Boolean = false,
    alpha: Float = 1.0F
) {
    val alignment = if (isMe) {
        Alignment.End
    } else {
        Alignment.Start
    }
    val shape = RoundedCornerShape(
        topStart = 10.dp,
        topEnd = 10.dp,
        bottomStart = if (isMe) 10.dp else 0.dp,
        bottomEnd = if (isMe) 0.dp else 10.dp
    )
    val backgroundColor = if (isMe) {
        COLOR_PRIMARY
    } else {
        COLOR_LIGHT
    }
    val color = if (isMe) {
        COLOR_WHITE
    } else {
        COLOR_BLACK
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .padding(
                    horizontal = 16.dp,
                    vertical = 2.dp
                ),
            horizontalAlignment = alignment
        ) {
            Text(
                modifier = Modifier
                    .clip(shape = shape)
                    .alpha(alpha)
                    .background(color = backgroundColor)
                    .padding(all = 8.dp),
                text = message,
                fontSize = 14.sp,
                color = color
            )
            if (!isBottomSame) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = name,
                    fontSize = 11.sp,
                    color = COLOR_DARK
                )
            }
        }
    }
}
