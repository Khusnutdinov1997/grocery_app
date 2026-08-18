package com.example.groceryapp.data.repository

import com.example.groceryapp.domain.model.Completed
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.domain.repository.OnboardingRepository
import com.example.groceryapp.utils.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : OnboardingRepository {
    override fun getOnboardingPages(range: IntRange?): List<OnboardingPage> {
        val allPages = listOf(
            OnboardingPage(
                title = "Get Discounts\nOn All Products",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                imageKey = "image_screen1"
            ),
            OnboardingPage(
                title = "Buy Premium\nQuality Fruits",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                imageKey = "image_screen2"            ),
            OnboardingPage(
                title = "Buy Quality\nDairy Products",
                description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                imageKey = "image_screen3"            ),
            OnboardingPage(
                title = "Premium Food\nAt Your Doorstep",
                description ="Lorem ipsum dolor sit amet, consetetur \nsadipscing elitr, sed diam nonumy",
                imageKey = "image_screen4"            ),
            OnboardingPage(
                title = "Buy Premium \n" +
                        "Quality Fruits",
                description ="Lorem ipsum dolor sit amet, consetetur \nsadipscing elitr, sed diam nonumy",
                imageKey = "image_screen5"            ),
            OnboardingPage(
                title = "Buy Quality \n" +
                        "Dairy Products",
                description ="Lorem ipsum dolor sit amet, consetetur \nsadipscing elitr, sed diam nonumy",
                imageKey = "image_screen6"            ),
            OnboardingPage(
                title = "Get Discounts \n" +
                        "On All Products",
                description ="Lorem ipsum dolor sit amet, consetetur \nsadipscing elitr, sed diam nonumy",
                imageKey = "image_screen7"            )
        )
        return range?.let { allPages.slice(it)} ?: allPages
    }

    override suspend fun saveOnboardingCompleted(completed: Boolean) {
        dataStoreManager.saveOnboardingCompleted(true)
    }

    override fun isOnboardingCompleted(): Flow<Boolean> {
        return dataStoreManager.getParamDataStore().map { preference ->
            preference.completed }
    }
}
