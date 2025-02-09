package com.example.soop2025.data.model.mapper

import com.example.soop2025.data.model.repossearch.ReposSearchItemResponse
import com.example.soop2025.data.model.repossearch.ReposSearchResponse
import com.example.soop2025.data.model.shared.OwnerResponse
import com.example.soop2025.domain.model.repossearch.ReposSearch
import com.example.soop2025.domain.model.repossearch.ReposSearchOwner
import com.example.soop2025.domain.model.repossearch.ReposSearches
import com.example.soop2025.domain.model.shared.Language

private const val DEFAULT_COLOR = "#AAAAAA"

fun ReposSearchResponse.toReposSearches(colors: Map<String, String>): ReposSearches {
    return ReposSearches(
        reposSearches = items.map { it.toReposSearch(colors) },
        totalCount = totalCount
    )
}

private fun ReposSearchItemResponse.toReposSearch(colors: Map<String, String>): ReposSearch {
    return ReposSearch(
        id = id,
        name = name,
        owner = owner?.toReposSearchOwner(),
        description = description,
        stargazersCount = stargazersCount,
        language = language?.let {
            Language(
                language = it,
                colorCode = colors[it] ?: DEFAULT_COLOR
            )
        }
    )
}

private fun OwnerResponse.toReposSearchOwner(): ReposSearchOwner {
    return ReposSearchOwner(
        id = id,
        name = login,
        profileImageUrl = avatarUrl
    )
}
