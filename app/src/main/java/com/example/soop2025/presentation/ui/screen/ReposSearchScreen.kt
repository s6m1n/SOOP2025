package com.example.soop2025.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.presentation.ReposSearchViewModel
import com.example.soop2025.presentation.ui.ReposSearchUiState
import com.example.soop2025.presentation.ui.component.CircularLoading
import com.example.soop2025.presentation.ui.component.SearchLazyColumn
import com.example.soop2025.presentation.ui.component.SearchTextField

@Composable
fun ReposSearchScreen(
    reposSearchViewModel: ReposSearchViewModel,
    onMoveReposScreen: (String, String) -> Unit
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
            ReposSearchUiState.Idle -> {}
            ReposSearchUiState.Loading -> CircularLoading()
            is ReposSearchUiState.Success -> HandleSuccessUiState(
                searchResultState = searchResultState,
                onMoveReposScreen = onMoveReposScreen,
                onScrollNewPage = { reposSearchViewModel.fetchNextPage() }
            )
            is ReposSearchUiState.Error -> HandleErrorUiState(searchResultState)
        }
    }
}

@Composable
private fun HandleSuccessUiState(
    searchResultState: ReposSearchUiState.Success,
    onMoveReposScreen: (String, String) -> Unit,
    onScrollNewPage: () -> Unit
) {
    SearchLazyColumn(
        items = searchResultState.data.reposSearches,
        onItemClicked = onMoveReposScreen,
        onScrollNewPage = onScrollNewPage
    )
}

@Composable
private fun HandleErrorUiState(
    searchResultErrorState: ReposSearchUiState.Error
) {
    val currentContext = LocalContext.current
    LaunchedEffect(Unit) {
        Toast.makeText(currentContext, searchResultErrorState.message, Toast.LENGTH_SHORT).show()
    }
}
