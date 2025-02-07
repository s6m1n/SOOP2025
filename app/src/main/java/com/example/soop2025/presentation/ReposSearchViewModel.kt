package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.ReposSearchRepository
import com.example.soop2025.domain.model.repossearch.ReposSearch
import com.example.soop2025.presentation.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposSearchViewModel @Inject constructor(
    private val reposSearchRepository: ReposSearchRepository
) : ViewModel() {

    private val _searchResultState = MutableStateFlow<UiState<List<ReposSearch>>>(UiState.Idle)
    val searchResultState: StateFlow<UiState<List<ReposSearch>>> get() = _searchResultState

    fun searchReposBy(repoName: String) {
        viewModelScope.launch {
            _searchResultState.emit(UiState.Loading)
            reposSearchRepository.searchRepositories(
                repoName,
                page = 1
            ).collect {
                when (it) {
                    is ResponseResult.Exception -> _searchResultState.emit(UiState.Error(it.message))
                    is ResponseResult.ServerError -> _searchResultState.emit(UiState.Error(it.message))
                    is ResponseResult.Success -> _searchResultState.emit(UiState.Success(it.data))
                }
            }
        }
    }
}
