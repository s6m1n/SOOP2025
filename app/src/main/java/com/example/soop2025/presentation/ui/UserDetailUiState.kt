package com.example.soop2025.presentation.ui

import com.example.soop2025.domain.model.user.UserDetail

sealed interface UserDetailUiState {
    data object Idle : UserDetailUiState
    data object Loading : UserDetailUiState
    data class Success(val data: UserDetail) : UserDetailUiState
    data class Error(val message: String) : UserDetailUiState
}
