package com.example.home_hackathon2.ui.widget

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home_hackathon2.ui.res.COLOR_BLACK
import com.example.home_hackathon2.ui.res.COLOR_DARK
import com.example.home_hackathon2.ui.res.COLOR_LIGHT

@Composable
fun BorderTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .border(width = 1.dp, color = COLOR_LIGHT, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp, vertical = 15.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = COLOR_BLACK,
                fontSize = 14.sp
            )
        )
        if (value.isEmpty()) {
            Text(
                text = hint,
                color = COLOR_DARK,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )
        }
    }
}
