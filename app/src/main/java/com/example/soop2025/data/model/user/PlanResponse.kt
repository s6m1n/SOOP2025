package com.example.soop2025.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanResponse(
    @SerialName("collaborators") val collaborators: Int,
    @SerialName("name") val name: String,
    @SerialName("space") val space: Int,
    @SerialName("private_repos") val privateRepos: Int
)
