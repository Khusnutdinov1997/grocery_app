package com.example.groceryapp.domain.model

data class CartSummary(
    val items: List<CartSummaryItem>,
    val shippingCharges: Double = 1.60
) {
    val subtotal: Double
        get() = items.sumOf { it.product.price * it.quantity }
    val total: Double
        get() = subtotal + shippingCharges
}

data class CartSummaryItem(
    val product: Product,
    val quantity: Int
)
