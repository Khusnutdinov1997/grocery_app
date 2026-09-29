package com.example.groceryapp.presentation.cart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.groceryapp.domain.model.CartSummaryItem
import com.example.groceryapp.presentation.home.ProductImageMapper

@Composable
fun CartItemCard(
    item: CartSummaryItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        val imageRes = ProductImageMapper.imageKeyToRes(item.product.imageUrl)

    }
}