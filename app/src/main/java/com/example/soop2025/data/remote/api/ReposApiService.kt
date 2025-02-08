package com.example.soop2025.data.remote.api

import com.example.soop2025.data.remote.model.response.repos.ReposResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposApiService {
    @GET(REPOS_ENDPOINT)
    suspend fun getRepos(
        @Path(OWNER_PARAM) ownerName: String,
        @Path(REPO_PARAM) repoName: String
    ): Response<ReposResponse>

    companion object {
        private const val REPOS_PATH = "/repos"
        private const val OWNER_PARAM = "owner"
        private const val REPO_PARAM = "repo"

        private const val REPOS_ENDPOINT = "$REPOS_PATH/{$OWNER_PARAM}/{$REPO_PARAM}"
    }
}
