package com.example.soop2025.data.remote.repository

import com.example.soop2025.data.ApiResponseHandler.handleApiResponse
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.data.remote.ResponseResult.Exception
import com.example.soop2025.data.remote.ResponseResult.Success
import com.example.soop2025.data.remote.api.UserApiService
import com.example.soop2025.data.remote.model.response.mapper.toUserDetail
import com.example.soop2025.data.remote.model.response.mapper.toUserRepository
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.domain.model.user.UserDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDefaultRepository @Inject constructor(
    private val userApiService: UserApiService
) : UserRepository {

    override suspend fun fetchUserDetail(userName: String): Flow<ResponseResult<UserDetail>> {
        val responseUserResult = handleApiResponse { userApiService.getUser(userName) }
        val responseRepoResult = handleApiResponse { userApiService.getUserRepos(userName) }

        return flow {
            emit(
                if (responseUserResult is Success && responseRepoResult is Success) {
                    Success(responseUserResult.data.toUserDetail(
                        repositories = responseRepoResult.data.toUserRepository()
                    ))
                } else {
                    if (responseUserResult is Exception) {
                        Exception(responseUserResult.e, responseUserResult.message)
                    } else if (responseRepoResult is Exception) {
                        Exception(responseRepoResult.e, responseRepoResult.message)
                    } else {
                        Exception(RuntimeException(), "server fail exception")
                    }
                }
            )
        }
    }
}
