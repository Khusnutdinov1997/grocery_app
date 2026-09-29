package com.example.groceryapp.data.repository

import com.example.groceryapp.data.local.CartEntity
import com.example.groceryapp.data.local.dao.CartDao
import com.example.groceryapp.data.local.toDomain
import com.example.groceryapp.domain.model.CartItem
import com.example.groceryapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {
    override fun observeCart(): Flow<List<CartItem>> {
        return cartDao.observeCart().map { entityList ->
            entityList.map { entity ->
                entity.toDomain()
            }
        }
    }

    override suspend fun addToCart(productId: String): Result<Unit> {
        return try {
            val existingItem = cartDao.getCartItemById(productId)
            if (existingItem != null) {
                cartDao.updateCartItem(
                    existingItem.copy(quantity = existingItem.quantity + 1)
                )
            } else {
                cartDao.insertCartItem(
                    CartEntity(productId = productId, quantity = 1)
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateQuantity(
        productId: String,
        quantity: Int
    ): Result<Unit> {
        return try {
            if (quantity <= 0) {
                cartDao.deleteCartItem(productId)
            } else {
                cartDao.insertCartItem(
                    CartEntity(productId = productId, quantity = quantity)
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun removeItem(productId: String): Result<Unit> {
        return try {
            cartDao.deleteCartItem(productId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearCart(): Result<Unit> {
        return try {
            cartDao.clearCart()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}