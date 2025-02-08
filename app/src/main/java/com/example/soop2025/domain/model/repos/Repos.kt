package com.example.soop2025.domain.model.repos

data class Repos(
    val id: Long,
    val repoName: String,
    val description: String?,
    val starCount: Int,
    val watchersCount: Int,
    val forksCount: Int,
    val language: String?,
    val userName: String,
    val userProfileImageUrl: String
)
