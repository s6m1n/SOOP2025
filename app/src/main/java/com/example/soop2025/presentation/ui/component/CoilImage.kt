package com.example.soop2025.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter

@Composable
fun CoilImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    placeHolder: Painter? = null
) {
    Image(
        painter = rememberAsyncImagePainter(
            model = imageUrl,
            placeholder = placeHolder
        ),
        contentDescription = contentDescription,
        modifier = modifier
    )
}
