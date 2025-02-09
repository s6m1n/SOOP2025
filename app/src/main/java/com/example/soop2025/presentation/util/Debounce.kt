package com.example.soop2025.presentation.util

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
inline fun debounced(
    crossinline onClick: () -> Unit,
    debounceTime: Long = 1000L
): () -> Unit {
    var lastTimeClicked by remember { mutableLongStateOf(0L) }
    val onClickLambda: () -> Unit = {
        val now = SystemClock.uptimeMillis()
        if (now - lastTimeClicked > debounceTime) {
            onClick()
        }
        lastTimeClicked = now
    }
    return onClickLambda
}

fun Modifier.debouncedClickable(
    onClick: () -> Unit,
    debounceTime: Long = 1000L
): Modifier {
    return this.composed {
        val clickable = debounced(onClick = { onClick() }, debounceTime = debounceTime)
        this.clickable { clickable() }
    }
}
