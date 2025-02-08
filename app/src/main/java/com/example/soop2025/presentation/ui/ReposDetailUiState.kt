package com.example.soop2025.presentation.ui

import com.example.soop2025.domain.model.repos.ReposDetail

sealed interface ReposDetailUiState {
    data object Idle : ReposDetailUiState
    data object Loading : ReposDetailUiState
    data class Success(val data: ReposDetail) : ReposDetailUiState
    data class Error(val message: String) : ReposDetailUiState
}
