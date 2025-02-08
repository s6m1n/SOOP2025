package com.example.soop2025.presentation.repos

import com.example.soop2025.domain.model.repos.Repos

sealed interface ReposUiState {
    data object Idle : ReposUiState
    data object Loading : ReposUiState
    data class Success(val data: Repos) : ReposUiState
    data class Error(val message: String) : ReposUiState
}
