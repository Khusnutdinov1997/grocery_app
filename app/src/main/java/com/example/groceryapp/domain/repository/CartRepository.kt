package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.CartItem
import com.example.groceryapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun observeCart(): Flow<List<CartItem>>
    suspend fun addToCart(productId: String): Result<Unit>
    suspend fun updateQuantity(productId: String, quantity: Int): Result<Unit>
    suspend fun removeItem(productId: String): Result<Unit>
    suspend fun clearCart(): Result<Unit>
}