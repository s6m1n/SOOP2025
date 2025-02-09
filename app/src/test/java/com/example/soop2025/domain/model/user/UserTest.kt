package com.example.soop2025.domain.model.user

import com.example.soop2025.domain.model.user.UserFixture.createUser
import com.example.soop2025.domain.model.user.UserFixture.createUserRepos
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserTest {

    @Test
    fun `User의 레포지토리가 3개면 repositoryCount 값은 3이다`() {
        val user = createUser(
            repositories = listOf(
                createUserRepos(id = 1L),
                createUserRepos(id = 2L),
                createUserRepos(id = 3L)
            )
        )

        assertEquals(3, user.repositoryCount)
    }

    @Test
    fun `User languages는 null을 제외한 중복 없는 언어 Set을 반환한다`() {
        val user = createUser(
            repositories = listOf(
                createUserRepos(id = 1L, language = "Kotlin"),
                createUserRepos(id = 2L, language = "Java"),
                createUserRepos(id = 3L, language = "Kotlin"),
                createUserRepos(id = 4L, language = null)
            )
        )

        val expectedLanguages = setOf("Kotlin", "Java")
        assertEquals(expectedLanguages, user.languages)
    }

    @Test
    fun `User languages는 모든 언어가 null이면 빈 Set을 반환한다`() {
        val user = createUser(
            repositories = listOf(
                createUserRepos(id = 1L, language = null),
                createUserRepos(id = 2L, language = null)
            )
        )

        assertTrue(user.languages.isEmpty())
    }

    @Test
    fun `createUser에 인자가 없으면 기본 User 객체를 생성한다`() {
        val user = createUser()

        assertEquals(1L, user.id)
        assertEquals("이소민", user.name)
        assertEquals(100, user.followers)
        assertEquals(50, user.following)
        assertEquals("https://example.com/profile.png", user.profileImageUrl)
        assertEquals("Android developer", user.bio)
        assertEquals(3, user.repositories.size)
    }

    @Test
    fun `createUser에 특정 값을 전달하면 해당 값으로 User 객체를 생성한다`() {
        val user = createUser(
            id = 42L,
            name = "민소이",
            followers = 500,
            following = 200,
            profileImageUrl = "https://example.com/johndoe.png",
            bio = "파이팅",
            repositories = listOf(
                createUserRepos(id = 101L, language = "Kotlin"),
                createUserRepos(id = 102L, language = "Java")
            )
        )

        assertEquals(42L, user.id)
        assertEquals("민소이", user.name)
        assertEquals(500, user.followers)
        assertEquals(200, user.following)
        assertEquals("https://example.com/johndoe.png", user.profileImageUrl)
        assertEquals("파이팅", user.bio)
        assertEquals(2, user.repositories.size)
        assertEquals("Kotlin", user.repositories[0].language)
        assertEquals("Java", user.repositories[1].language)
    }

    @Test
    fun `createUserRepos에 인자가 없으면 기본 UserRepos 객체를 생성한다`() {
        val userRepos = createUserRepos()

        assertEquals(1L, userRepos.id)
        assertEquals(1L, userRepos.id)
        assertEquals(null, userRepos.language)
    }

    @Test
    fun `createUserRepos에 특정 값을 전달하면 해당 값으로 UserRepos 객체를 생성한다`() {
        val userRepos = createUserRepos(
            id = 99L,
            language = "Swift"
        )

        assertEquals(99L, userRepos.id)
        assertEquals("Swift", userRepos.language)
    }
}
