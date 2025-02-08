package com.example.soop2025.presentation.repos.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserText(
    titleText: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = titleText,
            fontSize = 20.sp,
            modifier = Modifier
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 2.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserTextPreview() {
    UserText(
        titleText = "User Title",
        value = "124"
    )
}
