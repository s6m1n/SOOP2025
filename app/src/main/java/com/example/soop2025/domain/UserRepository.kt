package com.example.soop2025.domain

import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun fetchUser(userName: String): Flow<ResponseResult<User>>
}
