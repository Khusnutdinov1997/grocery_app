package com.example.groceryapp.domain.model

import com.google.firebase.Timestamp

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val unit: String,
    val imageUrl: String,
    val categoryId: String,
    val isNew: Boolean,
    val discountPercent: Long?,
    val isFavorite: Boolean,
    val createdAt: Timestamp?
)
