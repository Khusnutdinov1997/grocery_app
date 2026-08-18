package com.example.groceryapp.presentation.registration


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegistrationEvent{
    object NavigateToHome : RegistrationEvent()
    object NavigateToRegistration1 : RegistrationEvent()
    object NavigateToRegistration2 : RegistrationEvent()
    data class ShowError(val message: String) : RegistrationEvent()
}

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(RegistrationUiState())
    val authState = _authState.asStateFlow()

    private val _events = Channel<RegistrationEvent>()
    val events = _events.receiveAsFlow()

    fun onEmailChanged(newEmail: String) {
        _authState.update { state ->
            state.copy(
                email = newEmail
            )
        }
    }

    fun onPasswordChanged(newPassword: String) {
        _authState.update { state ->
            state.copy(
                password = newPassword
            )
        }
    }

    fun onPhoneChanged(newPhone: String) {
        _authState.update { state ->
            state.copy(
                phoneNumber = newPhone
            )
        }
    }
    fun onNavigateToRegistration1() {
        viewModelScope.launch { _events.send(RegistrationEvent.NavigateToRegistration1) }
    }

    fun onNavigateToRegistration2() {
        viewModelScope.launch { _events.send(RegistrationEvent.NavigateToRegistration2) }
    }

    fun login() {
        val state = _authState.value
        viewModelScope.launch {
            _authState.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.login(state.email, state.password)
            _authState.update { it.copy(isLoading = false) }

            result.onSuccess {
                _events.send(RegistrationEvent.NavigateToHome)
            }.onFailure { e ->
                val msg = e.message ?: "Unknown error"
                _authState.update { s -> s.copy(error = msg) }
                _events.send(RegistrationEvent.ShowError(msg))
            }
        }
    }

    fun signUp() {
        val state = _authState.value
        viewModelScope.launch {
            _authState.update { it.copy(isLoading = true, error = null) }
            val result = authRepository.signUp(state.email, state.password, state.phoneNumber)
            _authState.update { it.copy(isLoading = false) }

            result.onSuccess {
                _events.send(RegistrationEvent.NavigateToHome)
            }.onFailure { e ->
                val msg = e.message ?: "Unknown error"
                _authState.update { it.copy(error = msg) }
                _events.send(RegistrationEvent.ShowError(msg))
            }
        }
    }

}