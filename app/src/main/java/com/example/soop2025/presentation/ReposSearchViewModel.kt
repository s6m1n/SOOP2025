package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.data.ApiResponseHandler.onException
import com.example.soop2025.data.ApiResponseHandler.onSuccess
import com.example.soop2025.domain.ReposSearchRepository
import com.example.soop2025.domain.model.repossearch.ReposSearches
import com.example.soop2025.presentation.ui.ReposSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            ).collect { response ->
                response.onSuccess {
                    _searchResultState.emit(
                        ReposSearchUiState.Success(it)
                    )
                }.onException { _, message ->
                    _searchResultState.emit(ReposSearchUiState.Error(message))
                }
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
        ).collect { response ->
            response.onSuccess {
                _searchResultState.emit(
                    ReposSearchUiState.Success(reposSearches.mergeWith(it))
                )
            }.onException { _, message ->
                _searchResultState.emit(ReposSearchUiState.Error(message))
            }
        }
    }
}
