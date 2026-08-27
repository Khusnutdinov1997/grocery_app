package com.example.groceryapp.presentation.onboarding

sealed class OnboardingEvent {
    object NavigateToNext : OnboardingEvent()
    object NavigateToRegistration : OnboardingEvent()
}