package com.example.soop2025.data.remote.model.response.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LicenseResponse(
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String? = null,
    @SerialName("spdx_id") val spdxId: String,
    @SerialName("node_id") val nodeId: String
)
