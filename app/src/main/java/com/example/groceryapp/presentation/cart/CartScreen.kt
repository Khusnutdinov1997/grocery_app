package com.example.groceryapp.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.groceryapp.domain.model.CartSummary
import com.example.groceryapp.domain.model.CartSummaryItem
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.presentation.cart.component.CartEmptyContent
import com.example.groceryapp.presentation.cart.component.CartItemCard
import com.example.groceryapp.presentation.cart.component.CartSummaryBlock
import com.example.groceryapp.ui.theme.GroceryAppTheme
import com.example.groceryapp.ui.theme.LightGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    onCheckoutClick: () -> Unit,
    onBackClick: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Shopping Cart",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            when (uiState) {
                is CartUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = LightGreen
                    )
                }
                is CartUiState.Empty -> {
                    CartEmptyContent(onNavigateToHome = onNavigateToHome)
                }
                is CartUiState.Success -> {
                    CartContent(
                        summary = (uiState as CartUiState.Success).summary,
                        onIncrease = viewModel::onIncreaseQuantity,
                        onDecrease = viewModel::onDecreaseQuantity,
                        onRemove = viewModel::removeItem,
                        onCheckoutClick = {}
                    )
                }
                is CartUiState.Error -> {
                    Text(
                        text = (uiState as CartUiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun CartContent(
    summary: CartSummary,
    onIncrease: (String, Int) -> Unit,
    onDecrease: (String, Int) -> Unit,
    onRemove: (String) -> Unit,
    onCheckoutClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = summary.items,
                key = { it.product.id }
            ) { item ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { swipeValue ->
                        if (swipeValue == SwipeToDismissBoxValue.EndToStart) {
                            onRemove(item.product.id)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        val color =
                            if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                                Color.Red
                            } else Color.Transparent

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(12.dp))
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    content = {
                        CartItemCard(
                            item = item,
                            onDecrease = { onDecrease(item.product.id, item.quantity) },
                            onIncrease = { onIncrease(item.product.id, item.quantity) }
                        )
                    }
                )
            }
        }

        CartSummaryBlock(
            summary = summary,
            onCheckoutClick = onCheckoutClick
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CartContentPreview() {
    val mockProduct = Product(
        id = "1",
        name = "Fresh Broccoli",
        price = 2.22,
        unit = "1.50 lbs",
        imageUrl = "broccoli",
        categoryId = "veg",
        isNew = true,
        discountPercent = null,
        isFavorite = false,
        createdAt = null
    )

    val mockSummary = CartSummary(
        items = listOf(
            CartSummaryItem(product = mockProduct, quantity = 4),
            CartSummaryItem(
                product = mockProduct.copy(id = "2", name = "Avacoda", price = 3.99),
                quantity = 2
            )
        ),
        shippingCharges = 1.60
    )

    GroceryAppTheme {
        CartContent(
            summary = mockSummary,
            onIncrease = { _, _ -> },
            onDecrease = { _, _ -> },
            onRemove = { _ -> },
            onCheckoutClick = {}
        )
    }
}