package com.example.soop2025.domain

import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.model.repossearch.ReposSearch
import kotlinx.coroutines.flow.Flow

interface ReposSearchRepository {
    suspend fun searchRepositories(
        repositoryName: String,
        page: Int
    ): Flow<ResponseResult<List<ReposSearch>>>
}
