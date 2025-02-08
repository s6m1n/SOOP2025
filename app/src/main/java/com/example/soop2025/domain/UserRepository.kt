package com.example.soop2025.domain

import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.model.user.UserDetail
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun fetchUserDetail(userName: String): Flow<ResponseResult<UserDetail>>
}
