package com.example.groceryapp.presentation.ProductDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.R
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.presentation.home.ProductImageMapper
import com.example.groceryapp.ui.theme.LightGray
import com.example.groceryapp.utils.rememberAverageColor

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    productId: String,
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(productId) {
        viewModel.loadProduct(productId)
    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF7CB342))
            }
        }

        uiState.errorMessage != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.errorMessage ?: "Faulet",
                    color = Color.Red
                )
            }
        }

        uiState.product != null -> {
            ProductDetailContent(
                product = uiState.product!!,
                quantity = uiState.quantity,
                onBackClick = onBackClick,
                onFavorite = {},
                onIncrease = viewModel::onIncreaseQuantity,
                onDecrease = viewModel::onDecreaseQuantity,
                onAddToCart = viewModel::onAddToCart
            )
        }
    }
}

@Composable
fun ProductDetailContent(
    product: Product,
    quantity: Int,
    onFavorite: () -> Unit,
    onBackClick: () -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onAddToCart: () -> Unit
) {
    val imageRes = ProductImageMapper.imageKeyToRes(product.imageUrl)
    val bgColor by rememberAverageColor(imageRes)
    val domeShape = remember {
        GenericShape { size, _ ->
            val arcHeight = size.width / 3f
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height - arcHeight)
            quadraticBezierTo(
                size.width / 2f, size.height - 120f,
                0f, size.height - arcHeight
            )
            close()
        }
    }
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(color = bgColor, domeShape)
            )
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.Black
                )
            }

            Image(
                painter = painterResource(
                    id = imageRes
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(340.dp)
                    .padding(top = 50.dp)
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Fit
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(LightGray),

            ) {
            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${product.price}",
                        color = Color(0xFF7CB342),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    IconButton(
                        onClick = onFavorite
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            tint = Color.LightGray
                        )
                    }

                }

                Spacer(Modifier.height(8.dp))
                Text(
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = product.unit,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "(89 reviews)",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )
                }

                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Organic Mountain works as a seller for many organic growers of organic lemons." +
                            " Organic lemons are easy to spot in your produce aisle. " +
                            "They are just like regular lemons, but they will usually have a few " +
                            "more scars on the outside of the lemon skin. Organic lemons are considered " +
                            "to be the world's finest lemon for juicing",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    maxLines = 8,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color.White, RoundedCornerShape(12.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Quantity",
                        modifier = Modifier.padding(start = 16.dp),
                        color = Color.Gray
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "−",
                            modifier = Modifier
                                .clickable { onDecrease() }
                                .padding(horizontal = 16.dp),
                            fontSize = 24.sp,
                            color = Color(0xFF7CB342)
                        )
                        Text(
                            text = "${quantity}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "+",
                            modifier = Modifier
                                .clickable { onIncrease() }
                                .padding(horizontal = 16.dp),
                            fontSize = 24.sp,
                            color = Color(0xFF7CB342)
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Button(
                    onClick = onAddToCart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7CB342)
                    )
                ) {
                    Text(
                        text = "Add to cart",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun ProductDetailPreview() {
    val product = Product(
        id = "1",
        name = "Fresh Peach",
        price = 0.0,
        imageUrl = R.drawable.peach.toString(),
    )
    ProductDetailContent(
        product = product,
        quantity = 1,
        onDecrease = {},
        onIncrease = {},
        onBackClick = {},
        onAddToCart = {},
        onFavorite = {}
    )

}