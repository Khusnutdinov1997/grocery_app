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
    onClick: () -> Unit,
    onFirstSplashScreen: () -> Unit
) {
    val pages by viewModel.secondOnboardingPages.collectAsState()
    val pagerState = rememberPagerState(pageCount = {
        if (pages.isEmpty()) 0 else pages.size + 1
    })

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == pages.size && pages.isNotEmpty()) {
            onFirstSplashScreen()
        }
    }

    OnboardingContent2(
        pages = pages,
        pagerState = pagerState,
        onClick = onClick,
        onFirstSplashScreen = onFirstSplashScreen
    )
}

@Composable
fun OnboardingContent2(
    pages: List<OnboardingPage>,
    pagerState: PagerState,
    onClick: () -> Unit,
    onFirstSplashScreen: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { position ->
            if (position < pages.size) {
                OnboardingPagerItem2(
                    page = pages[position]
                )
            }
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
        PagerIndicator2(
            pageSize = pages.size,
            currentPage = pagerState.currentPage.coerceAtMost(pages.size - 1)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {/* Todo : переход на экран регистрации */ },
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

@Composable
fun OnboardingPagerItem2(page: OnboardingPage) {
    val domeShape = remember {
        GenericShape { size, _ ->
            val arcHeight = size.width / 6

            moveTo(0f, arcHeight)
            quadraticBezierTo(
                size.width / 2f, 0f,
                size.width, arcHeight
            )
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.51f)
                .align(Alignment.BottomCenter)
                .background(
                    color = White,
                    shape = domeShape
                )
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
fun PagerIndicator2(pageSize: Int, currentPage: Int) {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun OnboardingPreview() {
    val page = OnboardingPage(
        title = "Buy Quality \n" +
                "Dairy Products",
        description = "Lorem ipsum dolor sit amet, consetetur \nsadipscing elitr, sed diam nonumy",
        image = R.drawable.image_screen6
    )

    GroceryAppTheme {
        OnboardingPagerItem2(page)
    }
}