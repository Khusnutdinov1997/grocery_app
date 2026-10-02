package com.example.groceryapp.presentation.cart.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.domain.model.CartSummary
import com.example.groceryapp.domain.model.CartSummaryItem
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.ui.theme.LightGreen

@Composable
fun CartSummaryBlock(
    summary: CartSummary,
    onCheckoutClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp)
    ) {
        SummaryRow(
            label = "Subtotal",
            value = "$${String.format("%.2f", summary.subtotal)}"
        )
        Spacer(modifier = Modifier.height(12.dp))

        SummaryRow(
            label = "Shipping charges",
            value = "$${String.format("%.2f", summary.shippingCharges)}"
        )

        Spacer(modifier = Modifier.height(16.dp))


        HorizontalDivider(
            thickness = 0.5.dp,
            color = Color.LightGray.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "$${String.format("%.2f", summary.total)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCheckoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults
                .buttonColors(containerColor = LightGreen),
            shape = RoundedCornerShape(6.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text(
                text = "Checkout",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun SummaryRow(
    label: String,
    value: String
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CartSummaryBlockPreview() {
    // Создаем тестовые данные для отображения в превью
    val mockProduct = Product(
        id = "1",
        name = "Fresh Broccoli",
        price = 2.22,
        unit = "1.50 lbs",
        imageUrl = "",
        categoryId = "",
        isNew = false,
        discountPercent = null,
        isFavorite = false,
        createdAt = null
    )

    val mockSummary = CartSummary(
        items = listOf(CartSummaryItem(mockProduct, 4)),
        shippingCharges = 1.60
    )

    Box(modifier = Modifier.padding(16.dp)) {
        CartSummaryBlock(
            summary = mockSummary,
            onCheckoutClick = {}
        )
    }
}