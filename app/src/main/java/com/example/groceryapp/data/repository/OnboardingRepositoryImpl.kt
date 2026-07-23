package com.example.groceryapp.data.repository

import com.example.groceryapp.R
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor() : OnboardingRepository {
    override fun getOnboardingPages(): List<OnboardingPage> {
        return listOf(
            OnboardingPage(
                title = "Get Discounts\nOn All Products",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                image = R.drawable.image_screen1 // Временная заглушка
            ),
            OnboardingPage(
                title = "Buy Premium\nQuality Fruits",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                image = R.drawable.image_screen2
            ),
            OnboardingPage(
                title = "Buy Quality\nDairy Products",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                image = R.drawable.image_screen3
            )
        )
    }
}
