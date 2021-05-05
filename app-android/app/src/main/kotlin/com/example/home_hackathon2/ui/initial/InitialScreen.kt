package com.example.home_hackathon2.ui.initial


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_DARK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT
import com.example.home_hackathon2.ui.tools.rememberViewModel
import com.example.home_hackathon2.ui.widget.*
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun InitialScreen() {
    val viewModel = rememberViewModel {
        it.getInitialViewModel()
    }
    val context = LocalContext.current
    LaunchedEffect(true) {
        requestMicPermission(context)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paddingBottomIme(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 30.dp),
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
        ErrorSnackbarHost(
            modifier = Modifier.align(Alignment.BottomCenter),
            messages = viewModel.errorMessage.receiveAsFlow()
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

fun requestMicPermission(context: Context) {
    if (!hasPermissions(context)) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            1
        )
    }
}

fun hasPermissions(context: Context) = arrayOf(Manifest.permission.RECORD_AUDIO).all {
    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}
