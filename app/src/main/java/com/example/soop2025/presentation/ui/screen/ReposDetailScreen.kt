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
import com.example.soop2025.presentation.ReposViewModel
import com.example.soop2025.presentation.ui.ReposDetailUiState
import com.example.soop2025.presentation.ui.component.CircularLoading
import com.example.soop2025.presentation.ui.repo.ReposDetailView
import com.example.soop2025.presentation.ui.user.UserBottomSheet

@Composable
fun ReposDetailScreen(
    reposViewModel: ReposViewModel,
    userName: String,
    repoName: String
) {
    LaunchedEffect(key1 = Unit) { reposViewModel.fetchReposDetail(userName, repoName) }
    when (val state = reposViewModel.reposDetailState.collectAsStateWithLifecycle().value) {
        ReposDetailUiState.Idle -> {}
        ReposDetailUiState.Loading -> CircularLoading()
        is ReposDetailUiState.Success -> HandleSuccessUiState(
            state.data,
            userName,
            reposViewModel
        )

        is ReposDetailUiState.Error -> {}
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HandleSuccessUiState(
    reposDetail: ReposDetail,
    userName: String,
    reposViewModel: ReposViewModel
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ReposDetailView(
        reposDetail = reposDetail,
        onButtonClicked = {
            reposViewModel.fetchUser(userName)
            showBottomSheet = true
        }
    )
    if (showBottomSheet) {
        UserBottomSheet(
            reposViewModel = reposViewModel,
            userName = userName,
            sheetState = sheetState,
            closeBottomSheet = { showBottomSheet = false }
        )
    }
}
