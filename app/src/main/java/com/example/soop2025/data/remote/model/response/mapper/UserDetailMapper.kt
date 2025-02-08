package com.example.soop2025.data.remote.model.response.mapper

import com.example.soop2025.data.remote.model.response.user.UserReposResponse
import com.example.soop2025.data.remote.model.response.user.UserResponse
import com.example.soop2025.domain.model.user.UserDetail
import com.example.soop2025.domain.model.user.UserRepos

fun UserResponse.toUserDetail(repositories: List<UserRepos>): UserDetail {
    return UserDetail(
        id = id,
        name = login,
        followers = followers,
        following = following,
        profileImageUrl = avatarUrl,
        bio = bio,
        repositories = repositories
    )
}

fun List<UserReposResponse>.toUserRepository(): List<UserRepos> {
    return this.map {
        UserRepos(
            id = it.id,
            language = it.language
        )
    }
}
