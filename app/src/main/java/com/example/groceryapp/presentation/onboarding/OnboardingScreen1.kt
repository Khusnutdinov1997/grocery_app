package com.example.groceryapp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.ui.theme.*
import com.example.groceryapp.R
import kotlinx.coroutines.launch

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
