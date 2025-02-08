package com.example.soop2025.data.model.mapper

import com.example.soop2025.data.model.repos.ReposResponse
import com.example.soop2025.domain.model.repos.Repos

fun ReposResponse.toRepos(): Repos {
    return Repos(
        id = id,
        repoName = name,
        description = description,
        starCount = stargazersCount,
        watchersCount = watchersCount,
        forksCount = forksCount,
        language = language,
        userName = owner.login,
        userProfileImageUrl = owner.avatarUrl
    )
}
