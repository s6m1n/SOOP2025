package com.example.soop2025.presentation.repos.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import com.example.soop2025.presentation.repos.HandleErrorUiState
import com.example.soop2025.presentation.repos.UserUiState
import com.example.soop2025.presentation.ui.component.CircularLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    userState: UserUiState,
    userName: String,
    sheetState: SheetState,
    closeBottomSheet: () -> Unit
) {
    when (userState) {
        UserUiState.Idle -> {}
        UserUiState.Loading -> {
            CircularLoading()
        }
        is UserUiState.Success -> UserModalBottomSheet(
            userState.data,
            closeBottomSheet,
            sheetState,
            userName
        )
        is UserUiState.Error -> {
            HandleErrorUiState(userState.message)
        }
    }
}
