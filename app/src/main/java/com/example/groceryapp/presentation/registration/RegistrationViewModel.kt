package com.example.groceryapp.presentation.registration


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.repository.AuthRepository
import com.example.groceryapp.presentation.RegistrationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(RegistrationUiState())
    val authState = _authState.asStateFlow()

    fun singUp(email: String, password: String, phone: String) {
        viewModelScope.launch {
            _authState.update { state ->
                state.copy(
                    isLoading = true,
                    error = null
                )
            }

            val result = authRepository.signUp(email, password, phone)

            _authState.update { state ->
                state.copy(
                    isLoading = false,
                    authResult = result,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.update { state ->
                state.copy(
                    isLoading = true,
                    error = null
                )
            }
            val result = authRepository.login(email, password)

            _authState.update { state ->
                state.copy(
                    isLoading = false,
                    authResult = result,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }

    fun changeScreen(screenKey: String){
        _authState.update { state ->
            state.copy(nameScreen = screenKey)
        }
    }

    fun resetState() {
        _authState.value = RegistrationUiState()
    }

}