package com.example.soop2025.domain.model.repossearch

data class ReposSearch(
    val id: Int,
    val name: String,
    val description: String?,
    val stargazersCount: Int,
    val language: String?,
    val owner: ReposSearchOwner?
)
