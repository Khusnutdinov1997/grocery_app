package com.example.groceryapp.presentation.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.presentation.home.HomeScreen
import com.example.groceryapp.presentation.onboarding.OnboardingScreen1
import com.example.groceryapp.presentation.onboarding.OnboardingScreen2
import com.example.groceryapp.presentation.onboarding.OnboardingViewModel
import com.example.groceryapp.presentation.registration.RegistrationScreen1
import com.example.groceryapp.presentation.registration.RegistrationScreen2
import com.example.groceryapp.presentation.registration.RegistrationScreen3
import com.example.groceryapp.presentation.registration.RegistrationViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val registrationViewModel: RegistrationViewModel = hiltViewModel()
    val isCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState()

    if (isCompleted != null) {
        NavHost(
            navController = navController,
            startDestination = if (isCompleted == true) {
                Screens.RegistrationScreen1.route
            } else {
                Screens.Splash1.route
            }
        ) {
            composable(
                Screens.Splash1.route
            ) {
                OnboardingScreen1(
                    viewModel = onboardingViewModel,
                    onFinish = {
                        navController.navigate(Screens.Splash2.route)
                    }
                )
            }
            composable(
                Screens.Splash2.route
            ) {
                OnboardingScreen2(
                    viewModel = onboardingViewModel,
                    onFinish = {
                        onboardingViewModel.completedOnboardingSave()
                        navController.navigate(Screens.RegistrationScreen1.route) {
                            popUpTo(Screens.Splash1.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screens.RegistrationScreen1.route) {
                RegistrationScreen1(
                    viewModel = registrationViewModel,
                    onSignUpClick = {
                        navController.navigate(Screens.RegistrationScreen2.route)
                    },
                    onLoginClick = {

                    }
                )
            }
            composable(Screens.RegistrationScreen2.route) {
                RegistrationScreen2(
                    viewModel = registrationViewModel,
                    onBackClick = {},
                    onLoginClick = {},
                    onSignUpClick = {},

                    )
            }
            composable(Screens.RegistrationScreen3.route) {
                RegistrationScreen3(

                )
            }

            composable(Screens.Home.route) {
                HomeScreen()
            }
        }
    }
}