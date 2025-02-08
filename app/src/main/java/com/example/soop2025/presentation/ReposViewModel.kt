package com.example.soop2025.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soop2025.domain.ReposRepository
import com.example.soop2025.domain.UserRepository
import com.example.soop2025.presentation.ui.ReposUiState
import com.example.soop2025.presentation.ui.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val reposRepository: ReposRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _reposState = MutableStateFlow<ReposUiState>(ReposUiState.Idle)
    val reposState = _reposState.asStateFlow()

    private val _userState = MutableStateFlow<UserUiState>(UserUiState.Idle)
    val userState = _userState.asStateFlow()

    fun fetchRepos(userName: String, repoName: String) {
        viewModelScope.launch {
            _reposState.emit(ReposUiState.Loading)
            reposRepository.fetchRepos(userName, repoName)
                .catch {
                    _reposState.emit(ReposUiState.Error(it.message.orEmpty()))
                }.collect {
                    _reposState.emit(ReposUiState.Success(it))
                }
        }
    }

    fun fetchUser(userName: String) {
        viewModelScope.launch {
            _userState.emit(UserUiState.Loading)
            userRepository.fetchUser(userName)
                .catch {
                    _userState.emit(UserUiState.Error(it.message.orEmpty()))
                }.collect {
                    _userState.emit(UserUiState.Success(it))
                }
        }
    }
}
