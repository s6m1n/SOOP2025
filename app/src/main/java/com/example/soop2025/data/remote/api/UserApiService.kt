package com.example.soop2025.data.remote.api

import com.example.soop2025.data.remote.model.response.user.UserDetailResponse
import com.example.soop2025.data.remote.model.response.user.UserReposResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiService {
    @GET(USER_DETAIL_PATH)
    suspend fun getUserDetail(
        @Path(USERNAME_PARAM) userName: String
    ): Response<UserDetailResponse>

    @GET(USER_REPOS_PATH)
    suspend fun getUserRepos(
        @Path(USERNAME_PARAM) userName: String
    ): Response<List<UserReposResponse>>

    companion object {
        private const val USERS_PATH = "/users"
        private const val REPOS_PATH = "/repos"
        private const val USERNAME_PARAM = "username"

        private const val USER_DETAIL_PATH = "$USERS_PATH/{$USERNAME_PARAM}"
        private const val USER_REPOS_PATH = "$USERS_PATH$USERNAME_PARAM$REPOS_PATH"
    }
}
