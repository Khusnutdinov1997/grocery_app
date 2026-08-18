package com.example.groceryapp.presentation.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.groceryapp.presentation.registration.RegistrationEvent
import com.example.groceryapp.presentation.registration.RegistrationScreen1
import com.example.groceryapp.presentation.registration.RegistrationScreen2
import com.example.groceryapp.presentation.registration.RegistrationScreen3
import com.example.groceryapp.presentation.registration.RegistrationViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val registrationViewModel: RegistrationViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        registrationViewModel.events.collect { event ->
            when (event) {
                is RegistrationEvent.NavigateToHome -> {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.RegistrationScreen1.route) { inclusive = true }
                    }
                }
                is RegistrationEvent.NavigateToRegistration2 -> {
                    navController.navigate(Screens.RegistrationScreen2.route)
                }
                is RegistrationEvent.NavigateToRegistration1 -> {
                    navController.popBackStack()
                }
                else -> {}
            }
        }
    }

    NavHost(navController = navController, startDestination = Screens.RegistrationScreen1.route) {
        composable(Screens.RegistrationScreen1.route) {
            RegistrationScreen1(viewModel = registrationViewModel)
        }
        composable(Screens.RegistrationScreen2.route) {
            RegistrationScreen2(viewModel = registrationViewModel)
        }
        composable(Screens.Home.route) {
            HomeScreen()
        }
    }
}