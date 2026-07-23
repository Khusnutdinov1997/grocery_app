package com.example.groceryapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository
) : ViewModel() {

    private val _pages = MutableStateFlow<List<OnboardingPage>>(emptyList())
    val pages: StateFlow<List<OnboardingPage>> = _pages.asStateFlow()

    init {
        loadsPages()
    }

    private fun loadsPages(){
        _pages.value = repository.getOnboardingPages()
    }
}
