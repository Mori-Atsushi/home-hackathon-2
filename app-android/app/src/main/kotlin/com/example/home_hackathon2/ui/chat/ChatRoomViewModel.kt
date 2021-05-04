package com.example.home_hackathon2.ui.chat

import com.example.home_hackathon2.model.Chat
import com.example.home_hackathon2.model.User
import com.example.home_hackathon2.ui.tools.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class ChatRoomViewModel @Inject constructor(

): ViewModel() {
    // TODO: get chats from usecase
    private val dummy = listOf(
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
    private val _chats = MutableStateFlow(dummy)
    val chats: StateFlow<List<Chat>> = _chats
}
