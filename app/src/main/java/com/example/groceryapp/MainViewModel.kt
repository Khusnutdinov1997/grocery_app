package com.example.groceryapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.repository.AuthRepository
import com.example.groceryapp.domain.repository.OnboardingRepository
import com.example.groceryapp.utils.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val startDestination = combine(
        flow = onboardingRepository.isOnboardingCompleted(),
        flow2 = authRepository.isAuthenticated()
    ) { completed, authenticated ->
        when {
            !completed -> Screens.Splash1.route
            authenticated -> Screens.Home.route
            else -> Screens.RegistrationScreen1.route
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
}