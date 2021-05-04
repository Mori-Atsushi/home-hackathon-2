package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.model.ChatRoomItem
import com.example.home_hackathon2.model.User
import com.example.home_hackathon2.ui.res.*

@Composable
fun ChatList(
    items: List<ChatRoomItem>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = contentPadding,
    ) {
        items(items) {
            when (it) {
                is ChatRoomItem.Chat -> {
                    ChatRow(chat = it.value)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChatRow() {
    val user = User(
        id = "id",
        name = "chigichan24"
    )
    val chat = Chat(
        user = user,
        message = "message",
        isMe = true
    )
    ChatRow(chat = chat)
}


@Composable
private fun ChatRow(chat: Chat) {
    val alignment = if (chat.isMe) {
        Alignment.End
    } else {
        Alignment.Start
    }
    val shape = RoundedCornerShape(
        topStart = 10.dp,
        topEnd = 10.dp,
        bottomStart = if (chat.isMe) 10.dp else 0.dp,
        bottomEnd = if (chat.isMe) 0.dp else 10.dp
    )
    val backgroundColor = if (chat.isMe) {
        COLOR_PRIMARY
    } else {
        COLOR_LIGHT
    }
    val color = if (chat.isMe) {
        COLOR_WHITE
    } else {
        COLOR_BLACK
    }
    val name = if (chat.isMe) {
        "自分"
    } else {
        chat.user.name
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
                    vertical = 6.dp
                ),
            horizontalAlignment = alignment
        ) {
            Text(
                modifier = Modifier
                    .clip(shape = shape)
                    .background(color = backgroundColor)
                    .padding(all = 8.dp),
                text = chat.message,
                fontSize = 14.sp,
                color = color
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = name,
                fontSize = 11.sp,
                color = COLOR_DARK
            )
        }
    }
}
