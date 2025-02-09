package com.example.soop2025.data.model.repos

import com.example.soop2025.data.model.shared.OwnerResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReposResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("owner") val owner: OwnerResponse,
    @SerialName("description") val description: String? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("forks_count") val forksCount: Int,
    @SerialName("stargazers_count") val stargazersCount: Int,
    @SerialName("watchers_count") val watchersCount: Int
)
