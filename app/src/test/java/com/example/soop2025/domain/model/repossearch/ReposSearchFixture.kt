package com.example.soop2025.domain.model.repossearch

import com.example.soop2025.domain.model.shared.Language

object ReposSearchFixture {

    fun createReposSearch(
        id: Int = 1,
        name: String = "SOOP2025",
        description: String? = "SOOP2025 과제 레포",
        stargazersCount: Int = 10,
        language: Language? = null,
        owner: ReposSearchOwner? = null
    ): ReposSearch {
        return ReposSearch(
            id = id,
            name = name,
            description = description,
            stargazersCount = stargazersCount,
            language = language,
            owner = owner
        )
    }

    fun createReposSearches(
        size: Int,
        totalCount: Int = 50
    ): ReposSearches {
        val reposSearches = List(size) { index ->
            createReposSearch(id = index + 1, name = "Repo $index")
        }
        return ReposSearches(
            reposSearches = reposSearches,
            totalCount = totalCount
        )
    }
}
