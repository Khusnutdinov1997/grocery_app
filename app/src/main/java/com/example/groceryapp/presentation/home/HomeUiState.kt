package com.example.groceryapp.presentation.home

import com.example.groceryapp.domain.model.Category
import com.example.groceryapp.domain.model.Product

data class HomeUiState(
    val searchQuery: String = "",
    val categoryList: List<Category> = Category.entries,
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
