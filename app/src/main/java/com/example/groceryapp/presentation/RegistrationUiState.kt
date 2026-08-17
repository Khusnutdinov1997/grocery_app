package com.example.groceryapp.presentation

import com.example.groceryapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

data class RegistrationUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val authResult: Result<FirebaseUser?>? = null,
    val error: String? = null,
    val nameScreen: String = "image_registration1"
)