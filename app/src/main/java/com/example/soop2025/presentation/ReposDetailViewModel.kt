package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.ReposRepository
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.domain.model.repos.ReposDetail
import com.example.soop2025.domain.model.user.UserDetail
import com.example.soop2025.domain.model.user.UserRepositories
import com.example.soop2025.presentation.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposDetailViewModel @Inject constructor(
    private val reposRepository: ReposRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _reposDetailState = MutableStateFlow<UiState<ReposDetail>>(UiState.Idle)
    val reposDetailState = _reposDetailState.asStateFlow()

    private val _userDetailState = MutableStateFlow(UserDetail.ofEmpty())
    val userDetailState = _userDetailState.asStateFlow()

    private val _userRepoState = MutableStateFlow(UserRepositories(emptyList()))
    val userRepoState = _userRepoState.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun fetchDetail(userName: String, repoName: String) {
        viewModelScope.launch {
            _reposDetailState.emit(UiState.Loading)
            reposRepository.fetchReposDetail(userName, repoName).collect {
                _reposDetailState.value = it
            }
        }
    }

    fun fetchUser(userName: String) {
        viewModelScope.launch {
            userRepository.fetchUserDetail(userName).collect {
                when (it) {
                    is ResponseResult.Exception -> _errorMessage.emit(it.message)
                    is ResponseResult.ServerError -> _errorMessage.emit(it.message)
                    is ResponseResult.Success -> _userDetailState.emit(it.data)
                }
            }
        }
    }

    fun fetchUserRepos(userName: String) {
        viewModelScope.launch {
            userRepository.fetchUserRepos(userName).collect {
                when (it) {
                    is ResponseResult.Exception -> _errorMessage.emit(it.message)
                    is ResponseResult.ServerError -> _errorMessage.emit(it.message)
                    is ResponseResult.Success -> _userRepoState.emit(it.data)
                }
            }
        }
    }
}
