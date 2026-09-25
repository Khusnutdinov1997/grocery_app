package com.example.groceryapp.presentation.ProductDetail

import com.example.groceryapp.domain.model.Product

data class ProductDetailUiState(
    val product: Product? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val quantity: Int = 1
)
