package com.example.groceryapp.presentation.navigate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.groceryapp.MainViewModel
import com.example.groceryapp.presentation.ProductDetail.ProductDetailScreen
import com.example.groceryapp.presentation.home.HomeScreen
import com.example.groceryapp.presentation.onboarding.OnboardingScreen
import com.example.groceryapp.presentation.onboarding.TargetPage
import com.example.groceryapp.presentation.registration.AuthEvent
import com.example.groceryapp.presentation.registration.AuthViewModel
import com.example.groceryapp.presentation.registration.RegistrationEvent
import com.example.groceryapp.presentation.registration.RegistrationScreen1
import com.example.groceryapp.presentation.registration.RegistrationScreen2
import com.example.groceryapp.presentation.registration.RegistrationScreen3
import com.example.groceryapp.presentation.registration.RegistrationViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun NavGraph(
    mainViewModel: MainViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val startDestination by mainViewModel.startDestination.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.event.collect { event ->
            when (event) {
                is AuthEvent.NavigateToLogin -> {
                    registrationViewModel.resetForm()
                    navController.navigate(Screens.RegistrationScreen2.route) {
                        popUpTo(Screens.Home.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        registrationViewModel.events.collect { event ->
            when (event) {
                is RegistrationEvent.NavigateToHome -> {
                    authViewModel.onAuthSuccess()
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.RegistrationScreen1.route) { inclusive = true }
                        launchSingleTop = true
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

                is RegistrationEvent.ShowMessage -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    // Экран ожидании инициализации сессии/DataStore
    if (startDestination == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color(0xFF7CB342))
        }
    } else {

        NavHost(
            navController = navController,
            startDestination = startDestination!!
        ) {
            composable(Screens.Splash1.route) {
                OnboardingScreen(
                    targetPage = TargetPage.FIRST,
                    onNavigateToNext = {
                        navController.navigate(Screens.Splash2.route)
                    },
                    onNavigateToRegistration = {
                        navController.navigate(Screens.RegistrationScreen1.route) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Screens.Splash2.route) {
                OnboardingScreen(
                    targetPage = TargetPage.SECOND,
                    onNavigateToNext = {},
                    onNavigateToRegistration = {
                        navController.navigate(Screens.RegistrationScreen1.route) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
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
                    onLogout = authViewModel::logout,
                    onProductClick = { productId ->
                        navController.navigate(
                            Screens.ProductDetail.createRoute(productId)
                        )
                    }
                )
            }
            composable(
                route = Screens.ProductDetail.route,
                arguments = listOf(
                    navArgument(Screens.ProductDetail.ARG_PRODUCT_ID){
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val productId  = backStackEntry.arguments
                    ?.getString(Screens.ProductDetail.ARG_PRODUCT_ID)
                    .orEmpty()

                ProductDetailScreen(
                    productId = productId,
                    onBackClick = {navController.popBackStack()}
                )
            }
        }
    }
}