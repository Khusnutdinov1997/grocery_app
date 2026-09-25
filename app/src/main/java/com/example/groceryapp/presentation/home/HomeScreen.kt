package com.example.groceryapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
    onProductClick: (productId: String) -> Unit = {}
) {
    val uiState by homeViewModel.uiState.collectAsState()

    HomeContent(
        searchQuery = uiState.searchQuery,
        products = uiState.products,
        isLoading = uiState.isLoading,
        onSearchQueryChange = homeViewModel::onSearchQueryChange,
        onCategoryClick = onCategoryClick,
        onProductClick = onProductClick,
        onFavoriteClick = homeViewModel::onFavoriteClick,
        onAddToCartClick = { /* TODO: Добавление в корзину */ }
    )
}

@Composable
fun HomeContent(
    searchQuery: String = "",
    products: List<Product> = emptyList(),
    isLoading: Boolean = false,
    onSearchQueryChange: (String) -> Unit = {},
    onCategoryClick: (Category) -> Unit = {},
    onProductClick: (productId: String) -> Unit = {},
    onFavoriteClick: (Product) -> Unit = {},
    onAddToCartClick: (Product) -> Unit = {}
) {
    Scaffold(
        bottomBar = { HomeBottomNavigation() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                containerColor = Color(0xFF7CB342),
                shape = CircleShape,
                modifier = Modifier
                    .size(64.dp)
                    .offset(y = 45.dp, x = -25.dp)
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
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF7CB342))
            }
        } else {
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
                        query = searchQuery,
                        onQueryChange = onSearchQueryChange
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
                                category = category,
                                onClick = onCategoryClick
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
                            product = pair[0],
                            modifier = Modifier.weight(1f),
                            onFavoriteClick = onFavoriteClick,
                            onAddToCartClick = onAddToCartClick,
                            onProductClick = onProductClick
                        )
                        if (pair.size > 1) {
                            ProductCard(
                                product = pair[1],
                                modifier = Modifier.weight(1f),
                                onFavoriteClick = onFavoriteClick,
                                onAddToCartClick = onAddToCartClick,
                                onProductClick = onProductClick
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
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeContent()
}