package com.example.soop2025.presentation.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soop2025.R

@Composable
fun SearchTopBar(
    onSearchDone: (String) -> Unit
) {
    var value by rememberSaveable { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = { value = it },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            keyboardActions = KeyboardActions(onDone = { onSearchDone(value) }),
            modifier = Modifier
                .weight(1F)
        )
        Image(
            painter = painterResource(id = R.drawable.icon_reading_glasses),
            contentDescription = stringResource(id = R.string.reading_glasses),
            modifier = Modifier
                .background(Color.White)
                .align(Alignment.CenterVertically)
                .clickable {
                    onSearchDone(value)
                }
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchTextFieldPreview() {
    var searchValue by rememberSaveable { mutableStateOf("Hello") }
    Column {
        SearchTopBar(
            onSearchDone = { searchValue = it }
        )
    }
}
