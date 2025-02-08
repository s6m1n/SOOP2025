package com.example.soop2025.domain.model.user

data class UserDetail(
    val id: Long,
    val name: String,
    val followers: Int,
    val following: Int,
    val profileImageUrl: String,
    val bio: String?,
    val repositories: List<UserRepos>
) {
    val repositoryCount = repositories.size
    val languages = repositories.mapNotNull {
        it.language
    }.toSet()
}
