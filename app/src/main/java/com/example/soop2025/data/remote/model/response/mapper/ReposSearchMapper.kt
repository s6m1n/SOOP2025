package com.example.soop2025.data.remote.model.response.mapper

import com.example.soop2025.data.remote.model.response.repossearch.ReposSearchItemResponse
import com.example.soop2025.data.remote.model.response.repossearch.ReposSearchResponse
import com.example.soop2025.data.remote.model.response.shared.OwnerResponse
import com.example.soop2025.domain.model.repossearch.ReposSearch
import com.example.soop2025.domain.model.repossearch.ReposSearchOwner

fun ReposSearchResponse.toReposSearches(): List<ReposSearch> {
    return this.items.map { it.toReposSearch() }
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
