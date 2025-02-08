package com.example.soop2025.data.remote.api

import com.example.soop2025.data.remote.model.response.user.UserReposResponse
import com.example.soop2025.data.remote.model.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET(USERS_ENDPOINT)
    suspend fun getUser(
        @Path(USERNAME_PARAM) userName: String
    ): Response<UserResponse>

    @GET(USERS_REPOS_ENDPOINT)
    suspend fun getUserRepos(
        @Path(USERNAME_PARAM) userName: String
    ): Response<List<UserReposResponse>>

    companion object {
        private const val USERS_PATH = "/users"
        private const val REPOS_PATH = "/repos"
        private const val USERNAME_PARAM = "username"

        private const val USERS_ENDPOINT = "$USERS_PATH/{$USERNAME_PARAM}"
        private const val USERS_REPOS_ENDPOINT = "$USERS_PATH/{$USERNAME_PARAM}$REPOS_PATH"
    }
}
