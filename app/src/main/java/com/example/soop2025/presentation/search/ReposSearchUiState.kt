package com.example.soop2025.presentation.search

import com.example.soop2025.domain.model.repossearch.ReposSearches

sealed interface ReposSearchUiState {
    data object Idle : ReposSearchUiState
    data object Loading : ReposSearchUiState
    data class Success(val data: ReposSearches) : ReposSearchUiState
    data class Error(val message: String) : ReposSearchUiState
}
