package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.ApiResponseHandler.handleApiResponse
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.ReposApiService
import com.example.soop2025.data.remote.model.response.mapper.toRepos
import com.example.soop2025.domain.ReposRepository
import com.example.soop2025.domain.model.repos.Repos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReposDefaultRepository @Inject constructor(
    private val reposApiService: ReposApiService
) : ReposRepository {

    override suspend fun fetchReposDetail(
        ownerName: String,
        repoName: String
    ): Flow<ResponseResult<Repos>> {
        val responseResult = handleApiResponse { reposApiService.getRepos(ownerName, repoName) }
        return flow {
            emit(
                when (responseResult) {
                    is Success -> Success(responseResult.data.toRepos())
                    is Exception -> Exception(responseResult.e, responseResult.message)
                }
            )
        }
    }
}
