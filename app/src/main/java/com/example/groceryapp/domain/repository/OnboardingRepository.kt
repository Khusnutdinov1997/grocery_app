package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.OnboardingPage


interface OnboardingRepository {
    fun getOnboardingPages(range: IntRange? = null): List<OnboardingPage>
}
