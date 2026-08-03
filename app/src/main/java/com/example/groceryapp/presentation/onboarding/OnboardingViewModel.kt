package com.example.groceryapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository
) : ViewModel() {

    private val _firstOnboardingPages = MutableStateFlow<List<OnboardingPage>>(emptyList())
    val firstOnboardingPages = _firstOnboardingPages.asStateFlow()

    private val _secondOnboardingPages =MutableStateFlow<List<OnboardingPage>>(emptyList())
    val secondOnboardingPages = _secondOnboardingPages.asStateFlow()

    init {
        loadsPages()
    }

    private fun loadsPages(){
        _firstOnboardingPages.value = repository.getOnboardingPages(0..2)
        _secondOnboardingPages.value = repository.getOnboardingPages(3..6)
    }
}
