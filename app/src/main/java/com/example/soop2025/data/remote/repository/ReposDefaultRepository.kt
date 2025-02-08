package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.remote.ApiResponseHandler
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
    private val reposApiService: ReposApiService,
    private val apiResponseHandler: ApiResponseHandler
) : ReposRepository {

    override suspend fun fetchRepos(
        ownerName: String,
        repoName: String
    ): Flow<ResponseResult<Repos>> {
        val responseResult = apiResponseHandler.handle { reposApiService.getRepos(ownerName, repoName) }
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
