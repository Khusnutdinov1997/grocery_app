package com.example.groceryapp.presentation.registration

import androidx.lifecycle.ViewModel
import com.example.groceryapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow<Boolean?>(null)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    init{
        checkUserSession()
    }

    private fun checkUserSession(){
        val currentUser = authRepository.getCurrentUser()
        _isAuthenticated.value = currentUser != null
    }

    fun logout(){
        authRepository.logout()
        _isAuthenticated.value = false
    }
}