package com.example.groceryapp.presentation.navigate

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.presentation.home.HomeScreen
import com.example.groceryapp.presentation.onboarding.OnboardingScreen1
import com.example.groceryapp.presentation.onboarding.OnboardingScreen2
import com.example.groceryapp.presentation.onboarding.OnboardingViewModel
import com.example.groceryapp.presentation.registration.AuthViewModel
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
    val onboardingViewModel: OnboardingViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()

    val isOnboardingCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState()
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        registrationViewModel.events.collect { event ->
            when (event) {
                is RegistrationEvent.NavigateToHome -> {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }

                is RegistrationEvent.NavigateToRegistration2 -> {
                    navController.navigate(Screens.RegistrationScreen2.route)
                }

                is RegistrationEvent.NavigateToRegistration1 -> {
                    navController.popBackStack()
                }

                is RegistrationEvent.NavigateToRegistration3 -> {
                    navController.navigate(Screens.RegistrationScreen3.route)
                }

                is RegistrationEvent.ShowError -> {
                    makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    if (isOnboardingCompleted != null && isAuthenticated != null) {
        NavHost(
            navController = navController,
            startDestination = when {
                isOnboardingCompleted == false -> Screens.Splash1.route
                isAuthenticated == false -> Screens.RegistrationScreen1.route
                else -> Screens.Home.route
            }

        ) {
            composable(Screens.Splash1.route) {
                OnboardingScreen1(
                    viewModel = onboardingViewModel,
                    onFinish = {
                        navController.navigate(Screens.Splash2.route)
                    }
                )
            }
            composable(Screens.Splash2.route) {
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
                RegistrationScreen1(viewModel = registrationViewModel)
            }
            composable(Screens.RegistrationScreen2.route) {
                RegistrationScreen2(viewModel = registrationViewModel)
            }
            composable(Screens.RegistrationScreen3.route) {
                RegistrationScreen3(viewModel = registrationViewModel)
            }
            composable(Screens.Home.route) {
                HomeScreen(
                    onLogout = { authViewModel.logout() },
                    toRegisterScreen = {
                        navController.navigate(Screens.RegistrationScreen2.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}