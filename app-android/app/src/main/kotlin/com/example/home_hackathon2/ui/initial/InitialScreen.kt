package com.example.home_hackathon2.ui.initial


import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_DARK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.tools.rememberViewModel
import com.example.home_hackathon2.ui.widget.BorderTextField
import com.example.home_hackathon2.ui.widget.TintButton
import com.example.home_hackathon2.ui.widget.paddingBottomIme

@Composable
fun InitialScreen() {
    val viewModel = rememberViewModel {
        it.getInitialViewModel()
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 30.dp)
            .paddingBottomIme(),
        verticalArrangement = Arrangement.Center
    ) {
        Message(
            modifier = Modifier.padding(bottom = 80.dp),
        )
        Divider(
            modifier = Modifier.padding(bottom = 24.dp),
            color = COLOR_LIGHT
        )
        NameTextField(
            text = viewModel.name.collectAsState().value,
            onValueChange = viewModel::handleChangeName,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        TintButton(
            text = "はじめる",
            onClick = viewModel::submit
        )
    }
}

@Composable
private fun Message(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            color = COLOR_BLACK,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = "ようこそ"
        )
        Text(
            color = COLOR_BLACK,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            text = "音声入力で高速なコミュニーケーションを"
        )
    }
}

@Composable
private fun NameTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(bottom = 6.dp),
            color = COLOR_DARK,
            fontSize = 14.sp,
            text = "名前"
        )
        BorderTextField(
            value = text,
            onValueChange = onValueChange,
            hint = "名前を入力する"
        )
    }
}
