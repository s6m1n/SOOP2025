package com.example.soop2025.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.soop2025.presentation.ui.formatMetricSuffix

@Composable
fun RepoInformationText(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoInformationTextPreview() {
    RepoInformationText(
        "example",
        formatMetricSuffix(14254)
    )
}
