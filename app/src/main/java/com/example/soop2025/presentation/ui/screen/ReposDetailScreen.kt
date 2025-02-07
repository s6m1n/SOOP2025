package com.example.soop2025.presentation.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.presentation.ReposDetailViewModel
import com.example.soop2025.presentation.ui.UiState
import com.example.soop2025.presentation.ui.repo.ReposDetailView
import com.example.soop2025.presentation.ui.user.UserBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReposDetailScreen(
    reposDetailViewModel: ReposDetailViewModel,
    userName: String,
    repoName: String
) {
    LaunchedEffect(key1 = Unit) { reposDetailViewModel.fetchDetail(userName, repoName) }

    when (val state = reposDetailViewModel.reposDetailState.collectAsStateWithLifecycle().value) {
        is UiState.Success -> {
            var showBottomSheet by rememberSaveable { mutableStateOf(false) }
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ReposDetailView(
                reposDetail = state.data,
                onButtonClicked = { showBottomSheet = true }
            )
            if (showBottomSheet) {
                UserBottomSheet(
                    reposDetailViewModel = reposDetailViewModel,
                    userName = userName,
                    sheetState = sheetState,
                    closeBottomSheet = { showBottomSheet = false }
                )
            }
        }

        is UiState.Idle -> {}

        is UiState.Error -> {}

        is UiState.Loading -> {}
    }
}
