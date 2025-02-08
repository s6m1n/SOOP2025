package com.example.soop2025.domain

import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.model.repos.Repos
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    suspend fun fetchReposDetail(ownerName: String, repoName: String): Flow<ResponseResult<Repos>>
}
