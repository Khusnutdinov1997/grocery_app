package com.example.groceryapp.domain.model

data class Product(
    val name: String,
    val price: String,
    val unit: String,
    val image: Int,
    val isNew: Boolean = false,
    val discount: String? = null,
    val isFavorite: Boolean = false
)
