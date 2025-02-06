package com.example.soop2025.data.remote.model.response.repossearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReposSearchResponse(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("incomplete_results") val incompleteResults: Boolean,
    @SerialName("items") val items: List<ReposSearchItemResponse>
)
