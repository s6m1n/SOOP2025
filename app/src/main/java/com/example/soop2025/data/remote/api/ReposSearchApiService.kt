package com.example.soop2025.data.remote.api

import com.example.soop2025.data.model.repossearch.ReposSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReposSearchApiService {
    @GET(SEARCH_REPOSITORIES_ENDPOINT)
    suspend fun searchRepositories(
        @Query(QUERY_PARAM) repositoryName: String,
        @Query(PAGE_PARAM) page: Int
    ): Response<ReposSearchResponse>

    companion object {
        private const val SEARCH_PATH = "/search"
        private const val REPOSITORIES_PATH = "/repositories"
        private const val SEARCH_REPOSITORIES_ENDPOINT = "$SEARCH_PATH$REPOSITORIES_PATH"

        private const val QUERY_PARAM = "q"
        private const val PAGE_PARAM = "page"
    }
}
