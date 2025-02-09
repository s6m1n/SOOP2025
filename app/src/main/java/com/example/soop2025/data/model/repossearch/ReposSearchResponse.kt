package com.example.soop2025.data.model.repossearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReposSearchResponse(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("items") val items: List<ReposSearchItemResponse>
)
