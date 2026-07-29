package com.example.groceryapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.groceryapp.R

object OnboardingImageMapper {
    @DrawableRes
    fun toDrawableRes(imageKey: String): Int = when(imageKey){
        "image_screen1" -> R.drawable.image_screen1
        "image_screen2" -> R.drawable.image_screen2
        "image_screen3" -> R.drawable.image_screen3
        "image_screen4" -> R.drawable.image_screen4
        "image_screen5" -> R.drawable.image_screen5
        "image_screen6" -> R.drawable.image_screen6
        "image_screen7" -> R.drawable.image_screen7
        else -> R.drawable.image_screen1
    }
}