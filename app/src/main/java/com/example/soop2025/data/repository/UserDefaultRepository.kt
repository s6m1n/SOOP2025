package com.example.soop2025.data.repository

import com.example.soop2025.data.model.mapper.toUser
import com.example.soop2025.data.model.mapper.toUserRepository
import com.example.soop2025.data.model.user.UserReposResponse
import com.example.soop2025.data.model.user.UserResponse
import com.example.soop2025.data.remote.ApiResponseHandler
import com.example.soop2025.data.remote.NetworkFailException
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.UserApiService
import com.example.soop2025.domain.model.user.User
import com.example.soop2025.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.IllegalStateException
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    private val userApiService: UserApiService,
    private val apiResponseHandler: ApiResponseHandler
) : UserRepository {

    override suspend fun fetchUser(userName: String): Flow<User> =
        flow {
            coroutineScope {
                val userDeferred =
                    async { apiResponseHandler.handle { userApiService.getUser(userName) } }
                val userReposDeferred =
                    async { apiResponseHandler.handle { userApiService.getUserRepos(userName) } }

                val userResult = userDeferred.await()
                val userReposResult = userReposDeferred.await()

                if (userResult is Success && userReposResult is Success) {
                    emit(userResult.data.toUser(userReposResult.data.toUserRepository()))
                } else {
                    throw handleExceptionResponse(userResult, userReposResult)
                }
            }
        }

    private fun handleExceptionResponse(
        userResult: ResponseResult<UserResponse>,
        userReposResult: ResponseResult<List<UserReposResponse>>
    ): Exception {
        return when {
            userResult is ResponseResult.Exception -> NetworkFailException(userResult.e, userResult.message)
            userReposResult is ResponseResult.Exception -> NetworkFailException(
                userReposResult.e,
                userReposResult.message
            )
            else -> IllegalStateException("userResult와 userReposResult 모두 성공했으나 예외가 발생했습니다.")
        }
    }
}
