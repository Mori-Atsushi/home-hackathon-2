package com.example.home_hackathon2.ui.chat

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.home_hackathon2.model.ChatRoom
import com.example.home_hackathon2.model.ChatRoomItem
import com.example.home_hackathon2.ui.res.*

@Composable
fun ChatList(
    data: ChatRoom,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val listState = rememberLazyListState()
    AutoScrollBottom(data = data, state = listState)
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
    ) {
        itemsIndexed(data.items) { index, it ->
            when (it) {
                is ChatRoomItem.Chat -> {
                    val isBottomSame = if (index < data.items.lastIndex) {
                        val next = data.items[index + 1]
                        next is ChatRoomItem.Chat && it.value.user.id == next.value.user.id
                    } else {
                        it.value.isMe && data.myPendingChat != null
                    }
                    Chat(
                        chat = it.value,
                        isBottomSame = isBottomSame
                    )
                }
            }
        }
        item {
            data.myPendingChat?.let {
                Chat(myPartialMessage = it)
            }
        }
    }
}


@Composable
private fun AutoScrollBottom(data: ChatRoom, state: LazyListState) {
    LaunchedEffect(data) {
        if (data.isEmpty()) {
            return@LaunchedEffect
        }
        val layoutInfo = state.layoutInfo
        val lastIndex = data.lastIndex
        val isVisibleLast =
            layoutInfo.visibleItemsInfo.firstOrNull { it.index >= layoutInfo.totalItemsCount - 1 } != null
        if (isVisibleLast) {
            state.animateScrollToItem(lastIndex)
        }
    }
}
