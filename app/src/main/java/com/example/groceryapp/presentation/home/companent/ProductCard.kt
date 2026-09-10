package com.example.groceryapp.presentation.home.companent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onFavoriteClick: (Product) -> Unit,
    onAddToCartClick: (Product) -> Unit
) {
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
                        .background(Color(0xFFFFF3E0), RoundedCornerShape(4.dp))
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