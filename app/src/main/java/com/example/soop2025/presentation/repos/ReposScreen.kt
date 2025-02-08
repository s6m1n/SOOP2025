package com.example.soop2025.presentation.repos

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.domain.model.repos.Repos
import com.example.soop2025.presentation.repos.component.UserBottomSheet
import com.example.soop2025.presentation.ui.component.CircularLoading

@Composable
fun ReposScreen(
    reposViewModel: ReposViewModel = hiltViewModel(),
    userName: String,
    repoName: String
) {
    LaunchedEffect(key1 = Unit) { reposViewModel.fetchRepos(userName, repoName) }

    when (val reposState = reposViewModel.reposState.collectAsStateWithLifecycle().value) {
        ReposUiState.Idle -> {}
        ReposUiState.Loading -> CircularLoading()
        is ReposUiState.Success -> HandleSuccessUiState(
            reposState.data,
            userName,
            reposViewModel
        )

        is ReposUiState.Error -> { HandleErrorUiState(reposState.message) }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HandleSuccessUiState(
    repos: Repos,
    userName: String,
    reposViewModel: ReposViewModel
) {
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ReposDetail(
        repos = repos,
        onButtonClicked = {
            reposViewModel.fetchUser(userName)
            showBottomSheet = true
        }
    )
    if (showBottomSheet) {
        when (val userState = reposViewModel.userState.collectAsStateWithLifecycle().value) {
            UserUiState.Idle -> {}
            UserUiState.Loading -> { CircularLoading() }
            is UserUiState.Success -> {
                UserBottomSheet(
                    userState = userState,
                    userName = userName,
                    sheetState = sheetState
                ) { showBottomSheet = false }
            }
            is UserUiState.Error -> {}
        }
    }
}

@Composable
fun HandleErrorUiState(
    message: String
) {
    val currentContext = LocalContext.current
    LaunchedEffect(Unit) {
        Toast.makeText(currentContext, message, Toast.LENGTH_SHORT).show()
    }
}
