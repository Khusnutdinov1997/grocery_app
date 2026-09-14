package com.example.groceryapp.presentation.home

import androidx.annotation.DrawableRes
import com.example.groceryapp.R

object ProductImageMapper {
    @DrawableRes
    fun imageKeyToRes(key: String): Int = when (key) {
        "peach" -> R.drawable.peach
        "avocado" -> R.drawable.avacoda
        "pineapple" -> R.drawable.pineapple
        "grapes" -> R.drawable.grapes
        "pomegranate" -> R.drawable.pomegranate
        "broccoli" -> R.drawable.broccoli
        else -> R.drawable.peach
    }
}