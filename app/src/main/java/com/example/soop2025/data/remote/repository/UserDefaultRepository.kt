package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.remote.ApiResponseHandler
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.UserApiService
import com.example.soop2025.data.remote.model.response.mapper.toUser
import com.example.soop2025.data.remote.model.response.mapper.toUserRepository
import com.example.soop2025.data.remote.model.response.user.UserReposResponse
import com.example.soop2025.data.remote.model.response.user.UserResponse
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.domain.model.user.User
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    private val userApiService: UserApiService,
    private val apiResponseHandler: ApiResponseHandler
) : UserRepository {

    override suspend fun fetchUser(userName: String): Flow<ResponseResult<User>> =
        flow {
            coroutineScope {
                val userDeferred =
                    async { apiResponseHandler.handle { userApiService.getUser(userName) } }
                val userReposDeferred =
                    async { apiResponseHandler.handle { userApiService.getUserRepos(userName) } }

                val userResult = userDeferred.await()
                val userReposResult = userReposDeferred.await()

                if (userResult is Success && userReposResult is Success) {
                    emit(Success(userResult.data.toUser(userReposResult.data.toUserRepository())))
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
    ): Exception<User> {
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
