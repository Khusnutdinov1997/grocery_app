package com.example.groceryapp.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.groceryapp.data.local.CartEntity
import kotlinx.coroutines.flow.Flow

interface CartDao {
    @Query("SELECT * FROM cart_item")
    fun observeCart(): Flow<List<CartEntity>>

    @Query("SELECT * FROM cart_item WHERE productId = :productId LIMIT 1")
    suspend fun getCartItemById(productId: String): CartEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartEntity: CartEntity)

    @Update
    suspend fun updateCartItem(cartEntity: CartEntity)

    @Query("DELETE FROM cart_item WHERE productId = :productId")
    suspend fun deleteCartItem(productId: String)

    @Query("DELETE FROM cart_item")
    suspend fun clearCart()
}