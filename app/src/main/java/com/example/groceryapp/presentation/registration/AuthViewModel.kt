package com.example.groceryapp.presentation.registration

import android.util.Log
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
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private val _openLoginScreen = MutableStateFlow(false)
    val openLoginScreen = _openLoginScreen.asStateFlow()

    private val _event = Channel<AuthEvent>()
    val event = _event.receiveAsFlow()

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        val currentUser = authRepository.getCurrentUser()
        _isAuthenticated.value = currentUser != null
        Log.d("AUTH_DEBAG", "User is logged in: ${currentUser != null}")
    }

    fun onAuthSuccess() {
        _isAuthenticated.value = true
        _openLoginScreen.value = false
    }

    fun logout() {
        authRepository.logout()
        _isAuthenticated.value = false
        _openLoginScreen.value = true
        viewModelScope.launch {
            _event.send(AuthEvent.NavigateToLogin)
        }
    }
}