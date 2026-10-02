package com.example.groceryapp.presentation.ProductDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.domain.repository.CartRepository
import com.example.groceryapp.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun onAddToCart() {
        val currentProduct = uiState.value.product ?: return
        val selectedQuantity = uiState.value.quantity
        viewModelScope.launch {
            cartRepository.updateQuantity(currentProduct.id, selectedQuantity)
        }
    }

    fun loadProduct(productId: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, errorMessage = null)
            }
            val result = productRepository.getProduct(productId)
            result.onSuccess { product ->
                _uiState.update { state ->
                    state.copy(product = product, isLoading = false)
                }
            }
                .onFailure { exception ->
                    _uiState.update { state ->
                        state.copy(isLoading = false, errorMessage = exception.message)
                    }
                }
        }
    }

    fun onIncreaseQuantity() {
        _uiState.update { state ->
            state.copy(quantity = state.quantity + 1)
        }
    }

    fun onDecreaseQuantity(){
        _uiState.update {state ->
            if (state.quantity > 1) state.copy(quantity = state.quantity - 1)
            else state
        }
    }
}