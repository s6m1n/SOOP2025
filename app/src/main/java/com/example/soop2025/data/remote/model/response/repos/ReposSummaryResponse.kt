package com.example.soop2025.data.remote.model.response.repos

import com.example.soop2025.data.remote.model.response.shared.OwnerResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReposSummaryResponse(
    @SerialName("id") val id: Long,
    @SerialName("node_id") val nodeId: String,
    @SerialName("name") val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("owner") val owner: OwnerResponse,
    @SerialName("html_url") val htmlUrl: String
)
