package com.example.soop2025.data.repository

import com.example.soop2025.data.model.mapper.toRepos
import com.example.soop2025.data.remote.ApiResponseHandler
import com.example.soop2025.data.remote.ApiResponseHandler.Companion.onException
import com.example.soop2025.data.remote.ApiResponseHandler.Companion.onSuccess
import com.example.soop2025.data.remote.NetworkFailException
import com.example.soop2025.data.remote.api.ReposApiService
import com.example.soop2025.domain.model.repos.Repos
import com.example.soop2025.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReposDefaultRepository @Inject constructor(
    private val reposApiService: ReposApiService,
    private val apiResponseHandler: ApiResponseHandler
) : ReposRepository {

    override suspend fun fetchRepos(
        ownerName: String,
        repoName: String
    ): Flow<Repos> {
        val responseResult =
            apiResponseHandler.handle { reposApiService.getRepos(ownerName, repoName) }
        return flow {
            responseResult.onSuccess {
                emit(it.toRepos())
            }.onException { throwable, message ->
                throw NetworkFailException(throwable, message)
            }
        }
    }
}
