package com.example.soop2025.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.domain.model.repossearch.ReposSearches
import com.example.soop2025.domain.repository.ReposSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposSearchViewModel @Inject constructor(
    private val reposSearchRepository: ReposSearchRepository
) : ViewModel() {

    private val _searchResultState = MutableStateFlow<ReposSearchUiState>(ReposSearchUiState.Idle)
    val searchResultState: StateFlow<ReposSearchUiState> get() = _searchResultState

    private var searchKeyword: String = ""
    private var searchJob: Job? = null

    fun searchReposBy(repoName: String) {
        if (searchJob?.isActive == true) return
        searchKeyword = repoName

        searchJob = viewModelScope.launch {
            _searchResultState.emit(ReposSearchUiState.Loading)
            reposSearchRepository.searchRepositories(
                repositoryName = repoName,
                page = 1
            ).catch {
                _searchResultState.emit(ReposSearchUiState.Error(it.message.orEmpty()))
            }.collect {
                _searchResultState.emit(
                    ReposSearchUiState.Success(it)
                )
            }
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch {
            val state = searchResultState.value
            if (state is ReposSearchUiState.Success && state.data.hasNextPage) {
                fetchAndMergeSearchResults(state.data)
            }
        }
    }

    private suspend fun fetchAndMergeSearchResults(reposSearches: ReposSearches) {
        reposSearchRepository.searchRepositories(
            repositoryName = searchKeyword,
            page = reposSearches.nextPageIndex
        ).catch {
            _searchResultState.emit(ReposSearchUiState.Error(it.message.orEmpty()))
        }.collect {
            _searchResultState.emit(
                ReposSearchUiState.Success(reposSearches.mergeWith(it))
            )
        }
    }
}
