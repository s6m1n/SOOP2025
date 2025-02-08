package com.example.soop2025.presentation.ui

import com.example.soop2025.domain.model.repos.ReposDetail

sealed interface ReposUiState {
    data object Idle : ReposUiState
    data object Loading : ReposUiState
    data class Success(val data: ReposDetail) : ReposUiState
    data class Error(val message: String) : ReposUiState
}
