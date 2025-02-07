package com.example.soop2025.domain.model.user

data class UserRepositories(
    val repositories: List<UserRepos>
) {
    val repositoryCount = repositories.size
    val languages = repositories.mapNotNull {
        it.language
    }.toSet()
}
