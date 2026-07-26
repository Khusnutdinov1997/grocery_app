package com.example.groceryapp.presentation.navigate

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.presentation.onboarding.OnboardingScreen1
import com.example.groceryapp.presentation.onboarding.OnboardingScreen2
import com.example.groceryapp.presentation.onboarding.OnboardingViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun NavGraph(
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.Splash1.route
    ) {
        composable(
            Screens.Splash1.route
        ) {
            OnboardingScreen1(
                viewModel = viewModel,
                onClick = {},
                onNextSplashScreen = {
                    navController.navigate(Screens.Splash2.route)
                }
            )
        }
        composable(
            Screens.Splash2.route
        ){
            OnboardingScreen2(
                viewModel = viewModel,
                onClick = {},
                onFirstSplashScreen = {
                    navController.popBackStack()
                    navController.navigate(Screens.Splash1.route)
                }
            )
        }
    }

}