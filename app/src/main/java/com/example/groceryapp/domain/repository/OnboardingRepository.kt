package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.OnboardingPage
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun getOnboardingPages(range: IntRange? = null): List<OnboardingPage>
}
