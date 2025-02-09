package com.example.soop2025.domain.model.user

object UserFixture {

    fun createUserRepos(
        id: Long = 1L,
        language: String? = null
    ): UserRepos {
        return UserRepos(
            id = id,
            language = language
        )
    }

    fun createUser(
        id: Long = 1L,
        name: String = "이소민",
        followers: Int = 100,
        following: Int = 50,
        profileImageUrl: String = "https://example.com/profile.png",
        bio: String? = "Android developer",
        repositories: List<UserRepos> = listOf(
            createUserRepos(id = 1L, language = "Kotlin"),
            createUserRepos(id = 2L, language = "Java"),
            createUserRepos(id = 3L, language = null)
        )
    ): User {
        return User(
            id = id,
            name = name,
            followers = followers,
            following = following,
            profileImageUrl = profileImageUrl,
            bio = bio,
            repositories = repositories
        )
    }
}
