package com.example.groceryapp.presentation.cart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.domain.model.CartSummaryItem
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.presentation.home.ProductImageMapper
import com.example.groceryapp.ui.theme.GreenPrimary
import com.example.groceryapp.utils.rememberAverageColor

@Composable
fun CartItemCard(
    item: CartSummaryItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    val imageRes = ProductImageMapper.imageKeyToRes(item.product.imageUrl)
    val bgColor by rememberAverageColor(imageRes)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${item.product.price} x ${item.quantity}",
                color = Color(0xFF7CB342),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = item.product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = item.product.unit,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.height(80.dp)
        ) {
            Text(
                text = "+",
                modifier = Modifier
                    .clickable{onIncrease()}
                    .padding(4.dp),
                fontSize = 20.sp,
                color = GreenPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${item.quantity}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "-",
                modifier = Modifier
                    .clickable{onDecrease()}
                    .padding(4.dp),
                fontSize = 20.sp,
                color = GreenPrimary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartItemCardPreview() {
    val fakeProduct = Product(
        id = "1",
        name = "peach",
        price = 0.0,
        unit = "lsp",
        imageUrl = "peach",
        categoryId = "fruits",
        isNew = false,
        discountPercent = null,
        isFavorite = false,
        createdAt = null,
    )
    CartItemCard(
        item = CartSummaryItem(
            product = fakeProduct,
            quantity = 12
        ),
        onIncrease = {},
        onDecrease = {}
    )
}