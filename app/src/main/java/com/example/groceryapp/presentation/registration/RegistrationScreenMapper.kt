package com.example.groceryapp.presentation.registration

import androidx.annotation.DrawableRes
import com.example.groceryapp.R

object RegistrationScreenMapper {
    @DrawableRes
    fun toDrawableRes(imageKey: String): Int = when(imageKey){
        "image_registration1" -> R.drawable.image_registration1
        "image_registration2" -> R.drawable.image_registration2
        "image_registration3" -> R.drawable.image_registration3
        else -> R.drawable.image_registration1
    }
}