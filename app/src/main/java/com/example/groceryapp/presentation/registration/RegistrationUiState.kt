package com.example.groceryapp.presentation.registration

data class RegistrationUiState(
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)