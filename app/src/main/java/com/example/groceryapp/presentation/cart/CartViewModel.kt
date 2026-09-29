package com.example.groceryapp.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.model.CartSummary
import com.example.groceryapp.domain.model.CartSummaryItem
import com.example.groceryapp.domain.repository.CartRepository
import com.example.groceryapp.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
): ViewModel() {

    /*
    private val _uiState = MutableStateFlo<CartUiState>(CartUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var cachedProducts: List<Product> = emptyList()
    private var cachedCartItems: List<CartItem> = emptyList()

    init{
        observeDataManually()
    }

    private fun observeDataManually(): {
        viewModelScope.launch{
            productRepository.observeProducts().collect{ products ->
                cachedProducts = products
                updateCartSummary()
            }
       }

       viewModelScope.launch {
            cartRepository.observeCart().collect { cartItems ->
                cachedCartItems = cartItems
                updateCartSummary()
             }
        }
    }

    private fun updateCartSummary() {
        val cartItems = cachedCartItems
        val allProducts = cachedProducts

        if (cartItems.isEmpty()) {
            _uiState.value = CartUiState.Empty
            return
        }

    val summaryItems = cartItems.mapNotNull { cartItem ->
            val product = allProducts.find { it.id == cartItem.productId }
            if (product != null) {
                CartSummaryItem(product, cartItem.quantity)
            } else null
        }

        if (summaryItems.isEmpty()) {
            _uiState.value = CartUiState.Empty
        } else {
            _uiState.value = CartUiState.Success(CartSummary(summaryItems))
        }
    }
     */

    val uiState: StateFlow<CartUiState> = combine(
        productRepository.observeProducts(),
        cartRepository.observeCart()
    ){allProducts, cartItems ->
        if (cartItems.isEmpty()){
            CartUiState.Empty
        }else{
            val summaryItems = cartItems.mapNotNull { cartItem ->
                val product = allProducts.find { product ->
                    product.id == cartItem.productId
                }
                if (product != null){
                    CartSummaryItem(product, cartItem.quantity)
                }else null
            }
            if (summaryItems.isEmpty()) CartUiState.Empty
            else CartUiState.Success(CartSummary(summaryItems))
        }
    }.stateIn(
        scope = viewModelScope, // привязка к жизненному циклю
        started = SharingStarted.WhileSubscribed(5000),// время через которое поток будет остановлен, если свернуто приложение. Нужно для экономии заряда батареи.
        initialValue = CartUiState.Loading// пока данных нет, передает состояние загрузки
    )

    fun onIncreaseQuantity(productId: String, currentQuantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(productId, currentQuantity +1)
        }
    }

    fun onDecreaseQuantity(productId: String, currentQuantity: Int) {
        viewModelScope.launch {
            cartRepository.updateQuantity(productId, currentQuantity - 1)
        }
    }

    fun removeItem(productId: String) {
        viewModelScope.launch {
            cartRepository.removeItem(productId)
        }
    }
}