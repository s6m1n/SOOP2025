package com.example.soop2025.presentation.ui

import com.example.soop2025.domain.model.repossearch.ReposSearch

sealed interface ReposSearchUiState {
    data object Idle : ReposSearchUiState
    data object Loading : ReposSearchUiState
    data class Success(val items: List<ReposSearch>) : ReposSearchUiState
    data class Error(val message: String) : ReposSearchUiState
}
