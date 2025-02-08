package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.remote.ApiResponseHandler
import com.example.soop2025.data.remote.NetworkFailException
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.ReposSearchApiService
import com.example.soop2025.data.remote.model.response.mapper.toReposSearches
import com.example.soop2025.domain.ReposSearchRepository
import com.example.soop2025.domain.model.repossearch.ReposSearches
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReposSearchDefaultRepository @Inject constructor(
    private val reposSearchApiService: ReposSearchApiService,
    private val apiResponseHandler: ApiResponseHandler
) : ReposSearchRepository {
    override suspend fun searchRepositories(
        repositoryName: String,
        page: Int
    ): Flow<ReposSearches> {
        val responseResult =
            apiResponseHandler.handle { reposSearchApiService.searchRepositories(repositoryName, page) }
        return flow {
            when (responseResult) {
                is Success -> {
                    emit(
                        responseResult.data.toReposSearches()
                    )
                }

                is Exception -> throw NetworkFailException(responseResult.e, responseResult.message)
            }
        }
    }
}
