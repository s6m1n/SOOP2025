package com.example.soop2025.data.remote.model.response.mapper

import com.example.soop2025.data.remote.model.response.repos.ReposDetailResponse
import com.example.soop2025.domain.model.repos.ReposDetail

fun ReposDetailResponse.toReposDetail(): ReposDetail {
    return ReposDetail(
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
