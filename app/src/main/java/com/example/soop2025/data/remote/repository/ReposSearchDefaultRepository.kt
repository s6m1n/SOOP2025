package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.ApiResponseHandler.handleApiResponse
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.ReposSearchApiService
import com.example.soop2025.data.remote.model.response.mapper.toReposSearches
import com.example.soop2025.domain.ReposSearchRepository
import com.example.soop2025.domain.model.repossearch.ReposSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReposSearchDefaultRepository @Inject constructor(
    private val reposSearchApiService: ReposSearchApiService
) : ReposSearchRepository {
    override suspend fun searchRepositories(
        repositoryName: String,
        page: Int
    ): Flow<ResponseResult<List<ReposSearch>>> {
        val responseResult =
            handleApiResponse { reposSearchApiService.searchRepositories(repositoryName, page) }
        return flow {
            emit(
                when (responseResult) {
                    is Success -> Success(responseResult.data.toReposSearches())
                    is Exception -> Exception(responseResult.e, responseResult.message)
                }
            )
        }
    }
}
