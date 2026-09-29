package com.example.groceryapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.groceryapp.domain.model.CartItem

@Entity(tableName = "cart_item")
data class CartEntity(
    @PrimaryKey
    val productId: String,
    val quantity: Int
)

fun CartEntity.toDomain(): CartItem{
    return CartItem(
        productId = productId,
        quantity = quantity
    )
}
