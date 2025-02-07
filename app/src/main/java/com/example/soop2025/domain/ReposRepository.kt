package com.example.soop2025.domain

import com.example.soop2025.domain.model.repos.ReposDetail
import com.example.soop2025.presentation.ui.UiState
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    suspend fun fetchReposDetail(ownerName: String, repoName: String): Flow<UiState<ReposDetail>>
}
