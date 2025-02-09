package com.example.soop2025.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserReposResponse(
    @SerialName("id") val id: Long,
    @SerialName("language") val language: String? = null
)
