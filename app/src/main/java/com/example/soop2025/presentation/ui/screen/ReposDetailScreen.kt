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
import com.example.soop2025.domain.model.repos.ReposDetail
import com.example.soop2025.presentation.ReposDetailViewModel
import com.example.soop2025.presentation.ui.UiState
import com.example.soop2025.presentation.ui.component.CircularLoading
import com.example.soop2025.presentation.ui.repo.ReposDetailView
import com.example.soop2025.presentation.ui.user.UserBottomSheet

@Composable
fun ReposDetailScreen(
    reposDetailViewModel: ReposDetailViewModel,
    userName: String,
    repoName: String
) {
    LaunchedEffect(key1 = Unit) { reposDetailViewModel.fetchDetail(userName, repoName) }
    when (val state = reposDetailViewModel.reposDetailState.collectAsStateWithLifecycle().value) {
        is UiState.Idle -> {}
        is UiState.Loading -> CircularLoading()
        is UiState.Success -> HandleSuccessUiState(state.data, userName, reposDetailViewModel)
        is UiState.Error -> {}
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HandleSuccessUiState(
    reposDetail: ReposDetail,
    userName: String,
    reposDetailViewModel: ReposDetailViewModel
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ReposDetailView(
        reposDetail = reposDetail,
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
