package com.example.groceryapp.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.domain.model.Category
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.presentation.home.companent.CategoryItem
import com.example.groceryapp.presentation.home.companent.HomeBottomNavigation
import com.example.groceryapp.presentation.home.companent.ProductCard
import com.example.groceryapp.presentation.home.companent.PromoBanner
import com.example.groceryapp.presentation.home.companent.SearchBar
import com.example.groceryapp.presentation.home.companent.SectionHeader

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {
    val categories = listOf(
        Category("Vegetables", Color(0xFFE8F5E9), R.drawable.vegetables_icon),
        Category("Fruits", Color(0xFFFFF3E0), R.drawable.fruits_icon),
        Category("Beverages", Color(0xFFFFFDE7), R.drawable.beverages_icon),
        Category("Grocery", Color(0xFFF3E5F5), R.drawable.grocery_icon),
        Category("Edible oil", Color(0xFFE1F5FE), R.drawable.edible_oil_icon),
        Category("Household", Color(0xFFFCE4EC), R.drawable.household_icon),
        Category("Baby", Color(0xFFD2EFFF), R.drawable.baby_icon)
    )

    val products = listOf(
        Product("Fresh Peach", "$8.00", "dozen", R.drawable.peach),
        Product("Avocoda", "$7.00", "2.0 lbs", R.drawable.avacoda, isNew = true),
        Product("Pineapple", "$9.90", "1.50 lbs", R.drawable.pineapple, isFavorite = true),
        Product("Black Grapes", "$7.05", "5.0 lbs", R.drawable.grapes, discount = "-10%"),
        Product("Pomegranate", "$2.09", "1.50 lbs", R.drawable.pomegranate, isNew = true),
        Product("Fresh Broccoli", "$3.00", "1 kg", R.drawable.broccoli)
    )

    Scaffold(
        bottomBar = { HomeBottomNavigation() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = Color(0xFF7CB342),
                shape = CircleShape,
                modifier = Modifier
                    .size(64.dp)
                    .offset(y = 45.dp, x = -25.dp) // Вот этот оффсет заставит кнопку «врезаться» в бар
                    .shadow(8.dp, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.basket_icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFBFBFB)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { SearchBar() }
            item { PromoBanner() }
            item { SectionHeader("Categories") }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(categories) { category ->
                        CategoryItem(category)
                    }
                }
            }
            item { SectionHeader("Featured products") }
            items(products.chunked(2)) { pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProductCard(pair[0], modifier = Modifier.weight(1f))
                    if (pair.size > 1) {
                        ProductCard(pair[1], modifier = Modifier.weight(1f))
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(60.dp)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onLogout = {}
    )
}

