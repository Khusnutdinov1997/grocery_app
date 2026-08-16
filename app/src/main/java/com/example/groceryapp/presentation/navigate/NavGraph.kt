package com.example.groceryapp.presentation.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.presentation.onboarding.OnboardingScreen1
import com.example.groceryapp.presentation.onboarding.OnboardingScreen2
import com.example.groceryapp.presentation.onboarding.OnboardingViewModel
import com.example.groceryapp.presentation.registration.RegistrationScreen1
import com.example.groceryapp.presentation.registration.RegistrationViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: OnboardingViewModel = hiltViewModel()
    val isCompleted by viewModel.isOnboardingCompleted.collectAsState()

    if (isCompleted != null) {
        NavHost(
            navController = navController,
            startDestination = if (isCompleted == true) {
                Screens.Home.route
            } else {
                Screens.Splash1.route
            }
        ) {
            composable(
                Screens.Splash1.route
            ) {
                OnboardingScreen1(
                    viewModel = viewModel,
                    onFinish = {
                        navController.navigate(Screens.Splash2.route)
                    }
                )
            }
            composable(
                Screens.Splash2.route
            ) {
                OnboardingScreen2(
                    viewModel = viewModel,
                    onFinish = {
                        viewModel.completedOnboardingSave()
                        navController.navigate(Screens.Home.route) {
                            popUpTo(Screens.Splash1.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screens.Home.route) {
                HomeScreen()
            }
        }
    }
}