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

    private val _userDetailState = MutableStateFlow<UiState<UserDetail>>(UiState.Idle)
    val userDetailState = _userDetailState.asStateFlow()

    private val _userRepoState = MutableStateFlow<UiState<UserRepositories>>(UiState.Idle)
    val userRepoState = _userRepoState.asStateFlow()

    fun fetchDetail(userName: String, repoName: String) {
        viewModelScope.launch {
            _reposDetailState.emit(UiState.Loading)
            reposRepository.fetchReposDetail(userName, repoName).collect {
                _reposDetailState.emit(
                    when (it) {
                        is ResponseResult.Exception -> UiState.Error(it.message)
                        is ResponseResult.Success -> UiState.Success(it.data)
                    }
                )
            }
        }
    }

    fun fetchUser(userName: String) {
        viewModelScope.launch {
            _userDetailState.emit(UiState.Loading)
            userRepository.fetchUserDetail(userName).collect {
                _userDetailState.emit(
                    when (it) {
                        is ResponseResult.Exception -> UiState.Error(it.message)
                        is ResponseResult.Success -> UiState.Success(it.data)
                    }
                )
            }
        }
    }

    fun fetchUserRepos(userName: String) {
        viewModelScope.launch {
            _userRepoState.emit(UiState.Loading)
            userRepository.fetchUserRepos(userName).collect {
                _userRepoState.emit(
                    when (it) {
                        is ResponseResult.Exception -> UiState.Error(it.message)
                        is ResponseResult.Success -> UiState.Success(it.data)
                    }
                )
            }
        }
    }
}
