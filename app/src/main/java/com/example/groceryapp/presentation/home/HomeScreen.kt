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
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    toRegisterScreen: () -> Unit
) {
    val categories = listOf(
        Category("Vegetables", Color(0xFFE8F5E9), R.drawable.vegetables_icon),
        Category("Fruits", Color(0xFFFFF3E0), R.drawable.fruits_icon),
        Category("Beverages", Color(0xFFFFFDE7), R.drawable.beverages_icon),
        Category("Grocery", Color(0xFFF3E5F5), R.drawable.grocery_icon),
        Category("Edible oil", Color(0xFFE1F5FE), R.drawable.edible_oil_icon),
        Category("Household", Color(0xFFFCE4EC), R.drawable.household_icon),
    )

    val products = listOf(
        Product("Fresh Peach", "$8.00", "dozen", R.drawable.image_screen1),
        Product("Avocoda", "$7.00", "2.0 lbs", R.drawable.image_screen2, isNew = true),
        Product("Pineapple", "$9.90", "1.50 lbs", R.drawable.image_screen3, isFavorite = true),
        Product("Black Grapes", "$7.05", "5.0 lbs", R.drawable.image_screen4, discount = "-10%"),
        Product("Pomegranate", "$2.09", "1.50 lbs", R.drawable.image_screen5, isNew = true),
        Product("Fresh Broccoli", "$3.00", "1 kg", R.drawable.image_screen6)
    )

    Scaffold(
        bottomBar = { HomeBottomNavigation() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO */ },
                containerColor = Color(0xFF7CB342),
                shape = CircleShape,
                modifier = Modifier
                    .size(64.dp)
                    .offset(y = 56.dp, x = 130.dp)
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
        floatingActionButtonPosition = FabPosition.Center
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

@Composable
fun SearchBar() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search keywords..", color = Color.Gray) },
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.Tune, contentDescription = null) },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF3F3F3),
            unfocusedContainerColor = Color(0xFFF3F3F3),
            disabledContainerColor = Color(0xFFF3F3F3),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE0F2F1))
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = "20% off on your\nfirst purchase",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (index == 0) 12.dp else 6.dp, 6.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) Color(0xFF7CB342) else Color.LightGray)
                    ) { }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(category.color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = category.icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = category.name, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (product.isNew) {
                Text(
                    text = "NEW",
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color(0xFFFFF3E0), RoundedCornerShape(4.dec()))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    color = Color(0xFFFF9800)
                )
            }
            if (product.discount != null) {
                Text(
                    text = product.discount,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color(0xFFFFEBEE), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    fontSize = 10.sp,
                    color = Color(0xFFE91E63)
                )
            }

            IconButton(
                onClick = { TODO() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    if (product.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (product.isFavorite) Color.Red else Color.LightGray
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = product.image),
                        contentDescription = null,
                        modifier = Modifier.size(70.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = product.price, color = Color(0xFF7CB342), fontWeight = FontWeight.Bold)
                Text(text = product.name, fontWeight = FontWeight.Medium)
                Text(text = product.unit, fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(12.dp))
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.basket_icon),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp), tint = Color(0xFF7CB342)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Add to cart", fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun HomeBottomNavigation() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.home_icon), contentDescription = null, tint = Color(0xFF7CB342))
            }
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.user_icon), contentDescription = null, tint = Color.LightGray)
            }

            IconButton(onClick = {}) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = null, tint = Color.LightGray)
            }

            Spacer(modifier = Modifier.width(64.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        onLogout = {},
        toRegisterScreen = {}
    )
}

