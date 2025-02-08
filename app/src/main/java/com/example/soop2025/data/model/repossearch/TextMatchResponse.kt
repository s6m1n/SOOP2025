package com.example.soop2025.data.model.repossearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextMatchResponse(
    @SerialName("object_url") val objectUrl: String,
    @SerialName("object_type") val objectType: String?,
    @SerialName("property") val property: String,
    @SerialName("fragment") val fragment: String
)
