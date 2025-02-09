package com.example.soop2025.data.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("login") val login: String,
    @SerialName("id") val id: Long,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("bio") val bio: String?,
    @SerialName("followers") val followers: Int,
    @SerialName("following") val following: Int
)
