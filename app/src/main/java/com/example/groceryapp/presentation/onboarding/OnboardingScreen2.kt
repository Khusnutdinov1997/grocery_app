package com.example.groceryapp.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.ui.theme.GroceryAppTheme

@Composable
fun OnboardingScreen2(
    viewModel: OnboardingViewModel
) {
    val pages by viewModel.secondOnboardingPages.collectAsState()

    OnboardingContent(
        pages = pages,
        layoutStyle = OnboardingLayoutStyle.DOME,
        finishButtonText = "Get started",
        onFinish = { viewModel.onFinishClicked() }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingPreview2() {
    val mockPages = listOf(
        OnboardingPage(
            title = "Buy Quality \n" +
                    "Dairy Products",
            description = "Lorem ipsum dolor sit amet, consetetur \nsadipscing elitr, sed diam nonumy",
            imageKey = "image_screen6"
        )
    )

    GroceryAppTheme {
        OnboardingContent(
            pages = mockPages,
            layoutStyle = OnboardingLayoutStyle.DOME,
            finishButtonText = "Get started",
            onFinish = {}
        )
    }
}