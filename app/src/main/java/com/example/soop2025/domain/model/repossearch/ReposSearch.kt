package com.example.soop2025.domain.model.repossearch

import com.example.soop2025.domain.model.shared.Language

data class ReposSearch(
    val id: Int,
    val name: String,
    val description: String?,
    val stargazersCount: Int,
    val language: Language?,
    val owner: ReposSearchOwner?
)
