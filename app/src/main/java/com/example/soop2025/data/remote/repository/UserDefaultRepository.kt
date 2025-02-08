package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.ApiResponseHandler.handleApiResponse
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.UserApiService
import com.example.soop2025.data.remote.model.response.mapper.toUserDetail
import com.example.soop2025.data.remote.model.response.mapper.toUserRepository
import com.example.soop2025.data.remote.model.response.user.UserReposResponse
import com.example.soop2025.data.remote.model.response.user.UserResponse
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.domain.model.user.UserDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {

    override suspend fun fetchUserDetail(userName: String): Flow<ResponseResult<UserDetail>> =
        flow {
            coroutineScope {
                val userDeferred =
                    async { handleApiResponse { userApiService.getUser(userName) } }
                val userReposDeferred =
                    async { handleApiResponse { userApiService.getUserRepos(userName) } }

                val userResult = userDeferred.await()
                val userReposResult = userReposDeferred.await()

                if (userResult is Success && userReposResult is Success) {
                    emit(Success(userResult.data.toUserDetail(userReposResult.data.toUserRepository())))
                    return@coroutineScope
                } else {
                    emit(handleExceptionResponse(userResult, userReposResult))
                    return@coroutineScope
                }
            }
        }

    private fun handleExceptionResponse(
        userResult: ResponseResult<UserResponse>,
        userReposResult: ResponseResult<List<UserReposResponse>>
    ): Exception<UserDetail> {
        return when {
            userResult is Exception -> Exception(userResult.e, userResult.message)
            userReposResult is Exception -> Exception(
                userReposResult.e,
                userReposResult.message
            )
            else -> Exception(RuntimeException(), "Server failure")
        }
    }
}
