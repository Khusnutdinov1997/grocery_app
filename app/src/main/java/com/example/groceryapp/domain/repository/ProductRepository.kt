package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun observeProducts(): Flow<List<Product>>
    suspend fun toggleFavorite(productId: String, isFavorite: Boolean): Result<Unit>
}