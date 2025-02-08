package com.example.soop2025.domain

import com.example.soop2025.domain.model.repossearch.ReposSearches
import kotlinx.coroutines.flow.Flow

interface ReposSearchRepository {
    suspend fun searchRepositories(
        repositoryName: String,
        page: Int
    ): Flow<ReposSearches>
}
