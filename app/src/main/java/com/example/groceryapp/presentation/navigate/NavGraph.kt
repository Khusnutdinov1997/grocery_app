package com.example.groceryapp.presentation.navigate

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.groceryapp.presentation.home.HomeScreen
import com.example.groceryapp.presentation.onboarding.OnboardingScreen1
import com.example.groceryapp.presentation.onboarding.OnboardingScreen2
import com.example.groceryapp.presentation.onboarding.OnboardingViewModel
import com.example.groceryapp.utils.Screens

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash1.route
    ) {
        composable(
            Screens.Splash1.route
        ) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen1(
                viewModel = viewModel,
                onFinish = {
                    navController.navigate(Screens.Splash2.route)
                }
            )
        }
        composable(
            Screens.Splash2.route
        ){
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen2(
                viewModel = viewModel,
                onFinish = {
                    navController.navigate(Screens.Home.route){
                        popUpTo(Screens.Splash1.route){ inclusive = true}
                    }
                }
            )
        }
        composable(Screens.Home.route){
            HomeScreen()
        }
    }

}