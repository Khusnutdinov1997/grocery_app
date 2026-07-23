package com.example.groceryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.presentation.onboarding.OnboardingScreen
import com.example.groceryapp.presentation.onboarding.OnboardingViewModel
import com.example.groceryapp.ui.theme.GroceryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GroceryAppTheme {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(viewModel = viewModel)
            }
        }
    }
}
