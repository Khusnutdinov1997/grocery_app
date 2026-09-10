package com.example.groceryapp.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    homeViewModel: HomeViewModel = hiltViewModel(),
    onLogout: () -> Unit = {},
    onCategoryClick: (Category) -> Unit = {},
    onProductClick: (Product) -> Unit = {}
) {

}

@Composable
fun HomeContent(
    onLogout: () -> Unit = {},
    onCategoryClick: (Category) -> Unit = {},
    onProductClick: (Product) -> Unit = {}
) {

    val products = listOf(
        Product(id = 1, "Fresh Peach", "$8.00", "dozen", R.drawable.peach),
        Product(id = 2, "Avocoda", "$7.00", "2.0 lbs", R.drawable.avacoda, isNew = true),
        Product(id = 3, "Pineapple", "$9.90", "1.50 lbs", R.drawable.pineapple, isFavorite = true),
        Product(id = 4, "Black Grapes", "$7.05", "5.0 lbs", R.drawable.grapes, discount = "-10%"),
        Product(id = 5, "Pomegranate", "$2.09", "1.50 lbs", R.drawable.pomegranate, isNew = true),
        Product(id = 6, "Fresh Broccoli", "$3.00", "1 kg", R.drawable.broccoli)
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
                    .offset(
                        y = 45.dp,
                        x = -25.dp
                    ) // Вот этот оффсет заставит кнопку «врезаться» в бар
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
            item {
                SearchBar(
                    query = "",
                    onQueryChange = {}
                )
            }
            item { PromoBanner() }
            item { SectionHeader("Categories") }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(
                        Category.entries,
                        key = { it.name }
                    ) { category ->
                        CategoryItem(
                            category,
                            onClick = {}
                        )
                    }
                }
            }
            item { SectionHeader("Featured products") }
            items(products.chunked(2)) { pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ProductCard(
                        pair[0],
                        modifier = Modifier.weight(1f),
                        onFavoriteClick = {},
                        onAddToCartClick = {}
                    )
                    if (pair.size > 1) {
                        ProductCard(
                            pair[1],
                            modifier = Modifier.weight(1f),
                            onFavoriteClick = {},
                            onAddToCartClick = {}
                        )
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
    HomeContent(

    )
}

