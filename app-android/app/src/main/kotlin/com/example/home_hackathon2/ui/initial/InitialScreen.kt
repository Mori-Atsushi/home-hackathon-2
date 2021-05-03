package com.example.home_hackathon2.ui.initial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_GRAY

@Composable
fun InitialScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            color = COLOR_BLACK,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = "ようこそ"
        )
        Text(
            modifier = Modifier.padding(bottom = 80.dp),
            color = COLOR_BLACK,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            text = "音声入力で高速なコミュニーケーションを"
        )
        Divider(
            modifier = Modifier.padding(bottom = 24.dp),
            color = COLOR_GRAY
        )
    }
}
