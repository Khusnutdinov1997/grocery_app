package com.example.groceryapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.domain.repository.OnboardingRepository
import com.example.groceryapp.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _firstOnboardingPages = MutableStateFlow<List<OnboardingPage>>(emptyList())
    val firstOnboardingPages = _firstOnboardingPages.asStateFlow()

    private val _secondOnboardingPages = MutableStateFlow<List<OnboardingPage>>(emptyList())
    val secondOnboardingPages = _secondOnboardingPages.asStateFlow()

    private val _isOnboardingCompleted = MutableStateFlow<Boolean?>(null)
    val isOnboardingCompleted = _isOnboardingCompleted.asStateFlow()

    init {
        loadsPages()
        checkOnboardingStatus()
    }

    private fun loadsPages(){
        _firstOnboardingPages.value = repository.getOnboardingPages(0..2)
        _secondOnboardingPages.value = repository.getOnboardingPages(3..6)
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            val completed = dataStoreManager.getParamDataStore().first()
            _isOnboardingCompleted.value = completed.completed
        }
    }

    fun completedOnboardingSave() {
        viewModelScope.launch {
            dataStoreManager.saveOnboardingCompleted(true)
            _isOnboardingCompleted.value = true
        }
    }
}
