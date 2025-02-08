package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.data.remote.ResponseResult
import com.example.soop2025.domain.ReposRepository
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.presentation.ui.ReposUiState
import com.example.soop2025.presentation.ui.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val reposRepository: ReposRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _reposDetailState = MutableStateFlow<ReposUiState>(ReposUiState.Idle)
    val reposDetailState = _reposDetailState.asStateFlow()

    private val _userState = MutableStateFlow<UserUiState>(UserUiState.Idle)
    val userState = _userState.asStateFlow()

    fun fetchReposDetail(userName: String, repoName: String) {
        viewModelScope.launch {
            _reposDetailState.emit(ReposUiState.Loading)
            reposRepository.fetchReposDetail(userName, repoName).collect {
                _reposDetailState.emit(
                    when (it) {
                        is ResponseResult.Exception -> ReposUiState.Error(it.message)
                        is ResponseResult.Success -> ReposUiState.Success(it.data)
                    }
                )
            }
        }
    }

    fun fetchUser(userName: String) {
        viewModelScope.launch {
            _userState.emit(UserUiState.Loading)
            userRepository.fetchUser(userName)
                .collect {
                    _userState.emit(
                        when (it) {
                            is ResponseResult.Exception -> UserUiState.Error(it.message)
                            is ResponseResult.Success -> UserUiState.Success(it.data)
                        }
                    )
                }
        }
    }
}
