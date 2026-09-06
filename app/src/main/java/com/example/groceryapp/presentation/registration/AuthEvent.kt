package com.example.groceryapp.presentation.registration

sealed class AuthEvent {
    object NavigateToLogin : AuthEvent()
}