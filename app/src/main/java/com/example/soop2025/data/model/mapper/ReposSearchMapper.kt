package com.example.soop2025.data.model.mapper

import com.example.soop2025.data.model.repossearch.ReposSearchItemResponse
import com.example.soop2025.data.model.repossearch.ReposSearchResponse
import com.example.soop2025.data.model.shared.OwnerResponse
import com.example.soop2025.domain.model.repossearch.ReposSearch
import com.example.soop2025.domain.model.repossearch.ReposSearchOwner
import com.example.soop2025.domain.model.repossearch.ReposSearches

fun ReposSearchResponse.toReposSearches(): ReposSearches {
    return ReposSearches(
        reposSearches = items.map { it.toReposSearch() },
        totalCount = totalCount
    )
}

fun ReposSearchItemResponse.toReposSearch(): ReposSearch {
    return ReposSearch(
        id = id,
        name = name,
        owner = owner?.toReposSearchOwner(),
        description = description,
        stargazersCount = stargazersCount,
        language = language
    )
}

fun OwnerResponse.toReposSearchOwner(): ReposSearchOwner {
    return ReposSearchOwner(
        id = id,
        name = login,
        profileImageUrl = avatarUrl
    )
}
