package com.example.soop2025.presentation.ui

import com.example.soop2025.domain.model.user.User

sealed interface UserUiState {
    data object Idle : UserUiState
    data object Loading : UserUiState
    data class Success(val data: User) : UserUiState
    data class Error(val message: String) : UserUiState
}
