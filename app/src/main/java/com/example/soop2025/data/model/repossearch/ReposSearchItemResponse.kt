package com.example.soop2025.data.model.repossearch

import com.example.soop2025.data.model.shared.OwnerResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReposSearchItemResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("owner") val owner: OwnerResponse? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("stargazers_count") val stargazersCount: Int,
    @SerialName("language") val language: String? = null
)
