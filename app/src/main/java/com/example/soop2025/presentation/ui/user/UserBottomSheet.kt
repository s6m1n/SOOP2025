package com.example.soop2025.presentation.ui.user

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soop2025.presentation.ReposViewModel
import com.example.soop2025.presentation.ui.UserUiState
import com.example.soop2025.presentation.ui.component.CircularLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    reposViewModel: ReposViewModel,
    userName: String,
    sheetState: SheetState,
    closeBottomSheet: () -> Unit
) {
    val userState: UserUiState =
        reposViewModel.userState.collectAsStateWithLifecycle().value

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
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                Toast.makeText(context, userState.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
