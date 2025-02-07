package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.ApiResponseHandler.handleApiResponse
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.api.UserApiService
import com.example.soop2025.data.remote.model.response.mapper.toUserDetail
import com.example.soop2025.data.remote.model.response.mapper.toUserRepos
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.domain.model.user.UserDetail
import com.example.soop2025.domain.model.user.UserRepositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {
    override suspend fun fetchUserDetail(userName: String): Flow<ResponseResult<UserDetail>> {
        val responseResult = handleApiResponse { userApiService.getUserDetail(userName) }
        return flow {
            emit(
                when (responseResult) {
                    is ResponseResult.Success -> {
                        ResponseResult.Success(responseResult.data.toUserDetail())
                    }

                    is ResponseResult.Exception -> {
                        ResponseResult.Exception(responseResult.e, responseResult.message)
                    }

                    is ResponseResult.ServerError -> {
                        ResponseResult.ServerError(responseResult.status, responseResult.message)
                    }
                }
            )
        }
    }

    override suspend fun fetchUserRepos(userName: String): Flow<ResponseResult<UserRepositories>> {
        val responseResult = handleApiResponse { userApiService.getUserRepos(userName) }
        return flow {
            emit(
                when (responseResult) {
                    is ResponseResult.Success -> {
                        ResponseResult.Success(responseResult.data.toUserRepos())
                    }

                    is ResponseResult.Exception -> {
                        ResponseResult.Exception(responseResult.e, responseResult.message)
                    }

                    is ResponseResult.ServerError -> {
                        ResponseResult.ServerError(responseResult.status, responseResult.message)
                    }
                }
            )
        }
    }
}
