package com.example.soop2025.presentation.ui.user

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.presentation.ReposDetailViewModel
import com.example.soop2025.presentation.ui.UserDetailUiState
import com.example.soop2025.presentation.ui.component.CircularLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    reposDetailViewModel: ReposDetailViewModel,
    userName: String,
    sheetState: SheetState,
    closeBottomSheet: () -> Unit
) {
    val userDetailState: UserDetailUiState =
        reposDetailViewModel.userDetailState.collectAsStateWithLifecycle().value

    when (userDetailState) {
        UserDetailUiState.Idle -> {}
        UserDetailUiState.Loading -> {
            CircularLoading()
        }
        is UserDetailUiState.Success -> UserDetailModalBottomSheet(
            userDetailState.data,
            closeBottomSheet,
            sheetState,
            userName
        )
        is UserDetailUiState.Error -> {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                Toast.makeText(context, userDetailState.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
