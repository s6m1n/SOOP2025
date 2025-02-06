package com.example.soop2025.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.soop2025.presentation.ReposSearchViewModel
import com.example.soop2025.presentation.ui.component.SearchLazyColumn
import com.example.soop2025.presentation.ui.component.SearchTextField

@Composable
fun ReposSearchScreen(
    reposSearchViewModel: ReposSearchViewModel,
    onMoveReposDetailScreen: (String, String) -> Unit
) {
    var searchValue by rememberSaveable { mutableStateOf("") }

    Column {
        SearchTextField(
            value = searchValue,
            onValueChange = {
                searchValue = it
            },
            onSearchDone = {
                reposSearchViewModel.searchReposBy(searchValue)
            }
        )
        SearchLazyColumn(
            reposSearchViewModel.searchResult.collectAsState().value
        ) { userName, repoName ->
            onMoveReposDetailScreen(userName, repoName)
        }
    }
}
