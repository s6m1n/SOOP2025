package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.ReposRepository
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.presentation.ui.ReposDetailUiState
import com.example.soop2025.presentation.ui.UserDetailUiState
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

    private val _reposDetailState = MutableStateFlow<ReposDetailUiState>(ReposDetailUiState.Idle)
    val reposDetailState = _reposDetailState.asStateFlow()

    private val _userDetailState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Idle)
    val userDetailState = _userDetailState.asStateFlow()

    fun fetchReposDetail(userName: String, repoName: String) {
        viewModelScope.launch {
            _reposDetailState.emit(ReposDetailUiState.Loading)
            reposRepository.fetchReposDetail(userName, repoName).collect {
                _reposDetailState.emit(
                    when (it) {
                        is ResponseResult.Exception -> ReposDetailUiState.Error(it.message)
                        is ResponseResult.Success -> ReposDetailUiState.Success(it.data)
                    }
                )
            }
        }
    }

    fun fetchUserDetail(userName: String) {
        viewModelScope.launch {
            _userDetailState.emit(UserDetailUiState.Loading)
            userRepository.fetchUserDetail(userName)
                .collect {
                    _userDetailState.emit(
                        when (it) {
                            is ResponseResult.Exception -> UserDetailUiState.Error(it.message)
                            is ResponseResult.Success -> UserDetailUiState.Success(it.data)
                        }
                    )
                }
        }
    }
}
