package com.example.groceryapp.domain.model

import com.google.firebase.Timestamp

data class Product(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val unit: String = "",
    val imageUrl: String = "",
    val categoryId: String = "",
    val isNew: Boolean = false,
    val discountPercent: Long? = null,
    val isFavorite: Boolean = false,
    val createdAt: Timestamp? = null
)
