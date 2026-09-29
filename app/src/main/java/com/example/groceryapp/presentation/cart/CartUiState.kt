package com.example.groceryapp.presentation.cart

import com.example.groceryapp.domain.model.CartSummary

sealed interface CartUiState {
    object Loading: CartUiState
    object Empty: CartUiState
    data class Success(val summary: CartSummary): CartUiState
    data class Error( val message: String): CartUiState
}