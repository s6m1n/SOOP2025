package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.ReposSearchRepository
import com.example.soop2025.domain.model.repossearch.ReposSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposSearchViewModel @Inject constructor(
    private val reposSearchRepository: ReposSearchRepository
) : ViewModel() {

    private val _searchResult = MutableStateFlow<List<ReposSearch>>(emptyList())
    val searchResult: StateFlow<List<ReposSearch>> get() = _searchResult

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    fun searchReposBy(repoName: String) {
        viewModelScope.launch {
            reposSearchRepository.searchRepositories(
                repoName,
                page = 1
            ).collect {
                when (it) {
                    is ResponseResult.Exception -> _errorMessage.emit(it.message)
                    is ResponseResult.ServerError -> _errorMessage.emit(it.message)
                    is ResponseResult.Success -> _searchResult.emit(it.data)
                }
            }
        }
    }
}
