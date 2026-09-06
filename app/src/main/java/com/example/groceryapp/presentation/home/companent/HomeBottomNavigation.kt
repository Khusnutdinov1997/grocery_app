package com.example.groceryapp.presentation.home.companent

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.groceryapp.R

@Composable
fun HomeBottomNavigation() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        ConstraintLayout(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.navigationBars) // Отступ ТОЛЬКО для системной полоски
                .height(64.dp) // Чистая высота панели с иконками
                .fillMaxWidth()
        ) {
            val (home, profile, favorites) = createRefs()

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.constrainAs(home) {
                    start.linkTo(parent.start, margin = 24.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.home_icon),
                    contentDescription = null,
                    tint = Color(0xFF7CB342),
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.constrainAs(profile) {
                    start.linkTo(home.end)
                    end.linkTo(favorites.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.user_icon),
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(
                onClick = { /* TODO */ },
                modifier = Modifier.constrainAs(favorites) {
                    start.linkTo(profile.end)
                    end.linkTo(parent.end, margin = 80.dp) // Оставляем место под FAB справа
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}