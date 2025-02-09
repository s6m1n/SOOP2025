package com.example.soop2025.domain.model.repossearch

data class ReposSearches(
    val reposSearches: List<ReposSearch>,
    val totalCount: Int
) {
    init {
        require(reposSearches.size <= totalCount) {
            "reposSearches의 크기(${reposSearches.size})는 totalCount($totalCount) 보다 크지 않아야 합니다."
        }
    }

    val hasNextPage: Boolean
        get() = reposSearches.size < totalCount

    val nextPageIndex: Int
        get() = reposSearches.size / ITEMS_PER_PAGE + 1

    fun mergeWith(newReposSearch: ReposSearches): ReposSearches {
        return this.copy(
            reposSearches = reposSearches + newReposSearch.reposSearches,
            totalCount = newReposSearch.totalCount
        )
    }

    companion object {
        const val ITEMS_PER_PAGE = 30
    }
}
