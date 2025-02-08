package com.example.soop2025.domain

import com.example.soop2025.domain.model.repos.Repos
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    suspend fun fetchRepos(ownerName: String, repoName: String): Flow<Repos>
}
