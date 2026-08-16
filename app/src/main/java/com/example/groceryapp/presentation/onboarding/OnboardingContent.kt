package com.example.groceryapp.presentation.onboarding


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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.domain.model.OnboardingPage
import com.example.groceryapp.ui.theme.DarkGray
import com.example.groceryapp.ui.theme.LightGray
import com.example.groceryapp.ui.theme.MainGreen
import com.example.groceryapp.ui.theme.MediumGray
import com.example.groceryapp.ui.theme.White
import com.example.groceryapp.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch


enum class OnboardingLayoutStyle {
    FULL_SCREEN,
    DOME
}

@Composable
fun OnboardingContent(
    pages: List<OnboardingPage>,
    layoutStyle: OnboardingLayoutStyle,
    finishButtonText: String,
    onFinish: () -> Unit
) {
    if (pages.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    val isLastPage = pagerState.currentPage == pages.lastIndex

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { position ->
            when (layoutStyle) {
                OnboardingLayoutStyle.FULL_SCREEN -> OnboardingPagerItemFullScreen(page = pages[position])
                OnboardingLayoutStyle.DOME -> OnboardingPagerItemDome(page = pages[position])
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
            PagerIndicator(
                pageSize = pages.size,
                currentPage = pagerState.currentPage
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (isLastPage) {
                        onFinish()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MainGreen),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (isLastPage) finishButtonText else "Next",
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
private fun OnboardingPagerItemFullScreen(page: OnboardingPage) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = OnboardingImageMapper.toDrawableRes(page.imageKey)),
            contentDescription = page.title,
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
private fun OnboardingPagerItemDome(page: OnboardingPage) {
    val domeShape = remember {
        GenericShape { size, _ ->
            val arcHeight = size.width / 6f
            moveTo(0f, arcHeight)
            quadraticBezierTo(size.width / 2f, 0f, size.width, arcHeight)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = OnboardingImageMapper.toDrawableRes(page.imageKey)),
            contentDescription = page.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .background(color = White, shape = domeShape)
                .padding(top = 80.dp)
                .padding(horizontal = 40.dp),
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
            Spacer(modifier = Modifier.height(20.dp))
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
private fun PagerIndicator(
    pageSize: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageSize){ index ->
            Box(modifier = Modifier
                .size(if (index == currentPage) 10.dp else 8.dp)
                .clip(CircleShape)
                .background(if (index == currentPage) MainGreen else LightGray)
            )
        }
    }
}
