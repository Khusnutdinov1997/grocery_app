package com.example.groceryapp.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    targetPage: TargetPage,
    onNavigateToNext: () -> Unit,
    onNavigateToRegistration: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is OnboardingEvent.NavigateToNext -> onNavigateToNext()
                is OnboardingEvent.NavigateToRegistration -> onNavigateToRegistration()
            }
        }
    }

    when (targetPage) {
        TargetPage.FIRST -> {
            val firstPage by viewModel.firstOnboardingPages.collectAsState()
            OnboardingContent(
                pages = firstPage,
                layoutStyle = OnboardingLayoutStyle.FULL_SCREEN,
                finishButtonText = "Continue",
                onFinish = { viewModel.onNextClicked() }
            )
        }

        TargetPage.SECOND -> {
            val secondPage by viewModel.secondOnboardingPages.collectAsState()
            OnboardingContent(
                pages = secondPage,
                layoutStyle = OnboardingLayoutStyle.DOME,
                finishButtonText = "Get started",
                onFinish = { viewModel.onFinishClicked() }
            )
        }
    }
}