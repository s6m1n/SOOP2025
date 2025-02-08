package com.example.soop2025.data.model.repossearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PermissionsResponse(
    @SerialName("admin") val admin: Boolean,
    @SerialName("maintain") val maintain: Boolean? = null,
    @SerialName("push") val push: Boolean,
    @SerialName("triage") val triage: Boolean? = null,
    @SerialName("pull") val pull: Boolean
)
