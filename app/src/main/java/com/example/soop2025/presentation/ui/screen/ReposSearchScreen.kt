package com.example.soop2025.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.domain.model.repossearch.ReposSearch
import com.example.soop2025.presentation.ReposSearchViewModel
import com.example.soop2025.presentation.ui.UiState
import com.example.soop2025.presentation.ui.component.CircularLoading
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
            onValueChange = { searchValue = it },
            onSearchDone = { reposSearchViewModel.searchReposBy(searchValue) }
        )
        when (
            val searchResultState = reposSearchViewModel.searchResultState
                .collectAsStateWithLifecycle().value
        ) {
            is UiState.Idle -> {}
            is UiState.Success -> HandleSuccessUiState(searchResultState, onMoveReposDetailScreen)
            is UiState.Loading -> CircularLoading()
            is UiState.Error -> CircularLoading()
        }
    }
}

@Composable
private fun HandleSuccessUiState(
    searchResultState: UiState.Success<List<ReposSearch>>,
    onMoveReposDetailScreen: (String, String) -> Unit
) {
    SearchLazyColumn(
        searchResultState.data
    ) { userName, repoName ->
        onMoveReposDetailScreen(userName, repoName)
    }
}
