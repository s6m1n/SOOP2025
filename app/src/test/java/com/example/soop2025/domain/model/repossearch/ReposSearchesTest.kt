package com.example.soop2025.domain.model.repossearch

import com.example.soop2025.domain.model.repossearch.ReposSearchFixture.createReposSearch
import com.example.soop2025.domain.model.repossearch.ReposSearchFixture.createReposSearches
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ReposSearchesTest {

    @Test
    fun `reposSearches size가 totalCount보다 작으면 다음 페이지를 불러올 수 있다`() {
        val reposSearches = createReposSearches(
            size = 20,
            totalCount = 50
        )
        assertTrue(reposSearches.hasNextPage)
    }

    @Test
    fun `reposSearches size가 totalCount와 같으면 다음 페이지를 불러올 수 없다`() {
        val reposSearches = createReposSearches(
            size = 50,
            totalCount = 50
        )
        assertFalse(reposSearches.hasNextPage)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `reposSearches size가 totalCount보다 클 수 없다`() {
        val reposSearches = createReposSearches(
            size = 70,
            totalCount = 50
        )

        assertFalse(reposSearches.hasNextPage)
    }

    @Test
    fun `지금까지 가져온 검색 결과가 45개, 총 검색 결과가 100개라면 다음 페이지의 인덱스는 2이다`() {
        val reposSearches = createReposSearches(size = 45, totalCount = 100)

        assertEquals(2, reposSearches.nextPageIndex)
    }

    @Test
    fun `두 결과를 합친다`() {
        val reposSearches1 = createReposSearches(size = 30, totalCount = 100)
        val reposSearches2 = createReposSearches(size = 20, totalCount = 100)

        val mergedReposSearches = reposSearches1.mergeWith(reposSearches2)

        assertEquals(50, mergedReposSearches.reposSearches.size)
        assertEquals(100, mergedReposSearches.totalCount)
        assertTrue(mergedReposSearches.reposSearches.containsAll(reposSearches1.reposSearches))
        assertTrue(mergedReposSearches.reposSearches.containsAll(reposSearches2.reposSearches))
    }

    @Test
    fun `ReposSearchFixture에 인자가 없으면 기본 ReposSearch 객체를 생성한다`() {
        val reposSearch = createReposSearch()

        assertEquals(1, reposSearch.id)
        assertEquals("SOOP2025", reposSearch.name)
        assertEquals("SOOP2025 과제 레포", reposSearch.description)
        assertEquals(10, reposSearch.stargazersCount)
        assertEquals(null, reposSearch.language)
        assertEquals(null, reposSearch.owner)
    }

    @Test
    fun `ReposSearchFixture에 특정 값을 전달하면 해당 값으로 ReposSearch 객체를 생성한다`() {
        val reposSearch = createReposSearch(
            id = 99999,
            name = "SOOP",
            description = "레포지토리 설명입니다",
            stargazersCount = 1004,
            language = null,
            owner = null
        )

        assertEquals(99999, reposSearch.id)
        assertEquals("SOOP", reposSearch.name)
        assertEquals("레포지토리 설명입니다", reposSearch.description)
        assertEquals(1004, reposSearch.stargazersCount)
        assertEquals(null, reposSearch.language)
        assertEquals(null, reposSearch.owner)
    }
}
