package com.example.soop2025.data.remote.model.response.shared

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status: String,
    val message: String
)
