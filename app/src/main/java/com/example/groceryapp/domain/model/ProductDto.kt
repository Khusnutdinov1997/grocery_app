package com.example.groceryapp.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class ProductDto (
    @DocumentId
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val unit: String = "",
    val imageUrl: String = "",
    val categoryId: String = "",
    val isNew: Boolean = false,
    val discountPercent: Long? = null,
    val isFavorite: Boolean = false,
    val createAt: Timestamp? = null
)

fun ProductDto.toDomain(): Product{
    return Product(
        id = id,
        name = name,
        price = price,
        unit = unit,
        imageUrl = imageUrl,
        categoryId = categoryId,
        isNew = isNew,
        discountPercent = discountPercent,
        isFavorite = isFavorite,
        createdAt = createAt
    )
}