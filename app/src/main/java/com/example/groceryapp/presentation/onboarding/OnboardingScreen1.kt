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
    viewModel: OnboardingViewModel,
    onClick: () -> Unit,
    onNextSplashScreen: () -> Unit
) {
    val pages by viewModel.firstOnboardingPages.collectAsState()
    val pagerState = rememberPagerState(pageCount = {
        if (pages.isEmpty()) 0 else pages.size + 1
    })

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == pages.size && pages.isNotEmpty()){
            onNextSplashScreen()
        }
    }

    OnboardingContent1(
        pages = pages,
        pagerState = pagerState,
        onClick = onClick,
        onNextSplashScreen = onNextSplashScreen
    )
}

@Composable
fun OnboardingContent1(
    pages: List<OnboardingPage>,
    pagerState: PagerState,
    onClick: () -> Unit,
    onNextSplashScreen: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),

        ) { position ->
            if (position < pages.size) {
                OnboardingPagerItem1(page = pages[position])
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PagerIndicator1(
                pageSize = pages.size,
                currentPage = pagerState.currentPage.coerceAtMost(pages.size -1)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { /* Todo : переход на экран регистрации */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MainGreen),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Get started",
                    color = White,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun OnboardingPagerItem1(page: OnboardingPage) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 40.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = page.title,
                fontSize = 30.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = DarkGray,
                lineHeight = 36.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = page.description,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                color = MediumGray
            )
        }
    }

}

@Composable
fun PagerIndicator1(
    pageSize: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageSize) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentPage) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (index == currentPage) MainGreen else LightGray)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview1() {
    val mockPages = listOf(
        OnboardingPage(
            title = "Get Discounts\nOn All Products",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
            image = R.drawable.image_screen1
        ),
        OnboardingPage(
            title = "Buy Premium\nQuality Fruits",
            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr.",
            image = R.drawable.image_screen1
        )
    )
    val pagerState = rememberPagerState(pageCount = { mockPages.size })

    GroceryAppTheme {
        OnboardingContent1(
            pages = mockPages,
            pagerState = pagerState,
            onClick = {},
            onNextSplashScreen = {}
        )
    }
}
