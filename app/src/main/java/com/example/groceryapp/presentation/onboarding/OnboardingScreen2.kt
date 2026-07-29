package com.example.groceryapp.presentation.onboarding

import android.graphics.drawable.shapes.ArcShape
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.example.groceryapp.R
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.ui.theme.DarkGray
import com.example.groceryapp.ui.theme.GroceryAppTheme
import com.example.groceryapp.ui.theme.LightGray
import com.example.groceryapp.ui.theme.MainGreen
import com.example.groceryapp.ui.theme.MediumGray
import com.example.groceryapp.ui.theme.White
import com.example.groceryapp.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen2(
    viewModel: OnboardingViewModel,
    onFinish: () -> Unit
) {
    val pages by viewModel.secondOnboardingPages.collectAsState()

    OnboardingContent(
        pages = pages,
        layoutStyle = OnboardingLayoutStyle.DOME,
        finishButtonText = "Get started",
        onFinish = onFinish
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