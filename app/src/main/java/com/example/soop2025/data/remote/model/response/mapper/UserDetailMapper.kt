package com.example.soop2025.data.remote.model.response.mapper

import com.example.soop2025.data.remote.model.response.user.UserDetailResponse
import com.example.soop2025.data.remote.model.response.user.UserReposResponse
import com.example.soop2025.domain.model.user.UserDetail
import com.example.soop2025.domain.model.user.UserRepos
import com.example.soop2025.domain.model.user.UserRepositories

fun UserDetailResponse.toUserDetail(): UserDetail {
    return UserDetail(
        id = id,
        name = login,
        followers = followers,
        following = following,
        profileImageUrl = avatarUrl,
        bio = bio
    )
}

fun List<UserReposResponse>.toUserRepos(): UserRepositories {
    return UserRepositories(this.map { it.toUserRepository() })
}

private fun UserReposResponse.toUserRepository(): UserRepos {
    return UserRepos(
        id = id,
        language = language
    )
}
