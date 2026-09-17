package com.example.groceryapp.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _openLoginScreen = MutableStateFlow(false)
    val openLoginScreen = _openLoginScreen.asStateFlow()

    private val _event = Channel<AuthEvent>()
    val event = _event.receiveAsFlow()

    fun onAuthSuccess() {
        _openLoginScreen.value = false
    }

    fun logout() {
        authRepository.logout()
        _openLoginScreen.value = true
        viewModelScope.launch {
            _event.send(AuthEvent.NavigateToLogin)
        }
    }
}