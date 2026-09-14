package com.example.groceryapp.domain.model

import androidx.compose.ui.graphics.Color
import com.example.groceryapp.R

enum class Category(
    val nameCategory: String,
    val iconResId: Int,
    val colorBG: Color
) {
    VEGETABLES(
        nameCategory = "Vegetables",
        iconResId = R.drawable.vegetables_icon,
        colorBG = Color(0xFFE8F5E9)
    ),
    FRUITS(
        nameCategory = "Fruits",
        colorBG = Color(0xFFFFF3E0),
        iconResId = R.drawable.fruits_icon
    ),
    BEVERAGES(
        nameCategory = "Beverages",
        colorBG = Color(0xFFFFFDE7),
        iconResId = R.drawable.beverages_icon
    ),
    GROCERY(
        nameCategory = "Grocery",
        colorBG = Color(0xFFF3E5F5),
        iconResId = R.drawable.grocery_icon
    ),
    EDIBLE_OIL(
        nameCategory = "Edible oil",
        colorBG = Color(0xFFE1F5FE),
        iconResId = R.drawable.edible_oil_icon
    ),
    HOUSEHOLD(
        nameCategory = "Household",
        colorBG = Color(0xFFFCE4EC),
        iconResId = R.drawable.household_icon
    ),
    BABY(
        nameCategory = "Baby",
        colorBG = Color(0xFFD2EFFF),
        iconResId = R.drawable.baby_icon
    )
}