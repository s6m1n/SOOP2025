package com.example.soop2025.data.model.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerResponse(
    @SerialName("id") val id: Int,
    @SerialName("login") val login: String,
    @SerialName("avatar_url") val avatarUrl: String
)
