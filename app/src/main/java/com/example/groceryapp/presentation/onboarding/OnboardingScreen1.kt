package com.example.groceryapp.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.ui.theme.GroceryAppTheme

@Composable
fun OnboardingScreen1(
    viewModel: OnboardingViewModel
) {
    val pages by viewModel.firstOnboardingPages.collectAsState()

    OnboardingContent(
        pages = pages,
        layoutStyle = OnboardingLayoutStyle.FULL_SCREEN,
        finishButtonText = "Continue",
        onFinish = { viewModel.onNextClicked() }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview1() {
    val mockPages = listOf(
        OnboardingPage(
            title = "Get Discounts\nOn All Products",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
            imageKey = "image_screen1"
        ),
        OnboardingPage(
            title = "Buy Premium\nQuality Fruits",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
            imageKey = "image_screen2"
        )
    )

    GroceryAppTheme {
        OnboardingContent(
            pages = mockPages,
            layoutStyle = OnboardingLayoutStyle.FULL_SCREEN,
            finishButtonText = "Continue",
            onFinish = {}
        )
    }
}
