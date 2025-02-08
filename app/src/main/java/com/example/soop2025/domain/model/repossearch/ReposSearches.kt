package com.example.soop2025.domain.model.repossearch

data class ReposSearches(
    val reposSearches: List<ReposSearch>,
    val totalCount: Int
) {
    val hasNextPage: Boolean
        get() = reposSearches.size < totalCount

    val nextPageIndex: Int
        get() = reposSearches.size / ITEMS_PER_PAGE + 1

    fun mergeWith(newReposSearch: ReposSearches): ReposSearches {
        return this.copy(reposSearches = reposSearches + newReposSearch.reposSearches)
    }

    companion object {
        const val ITEMS_PER_PAGE = 30
    }
}
