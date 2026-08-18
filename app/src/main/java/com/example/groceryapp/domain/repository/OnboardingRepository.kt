package com.example.groceryapp.domain.repository

import com.example.groceryapp.domain.model.Completed
import com.example.groceryapp.domain.model.OnboardingPage
import kotlinx.coroutines.flow.Flow


interface OnboardingRepository {
    fun getOnboardingPages(range: IntRange? = null): List<OnboardingPage>
    suspend fun saveOnboardingCompleted(completed: Boolean)
    fun isOnboardingCompleted(): Flow<Boolean>
}
