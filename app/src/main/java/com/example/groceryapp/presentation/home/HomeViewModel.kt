package com.example.groceryapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.domain.model.Product
import com.example.groceryapp.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val _homeUiEvent = Channel<HomeUiEvent>()
    val homeUiEvent = _homeUiEvent.receiveAsFlow()

    init {
        observeProducts()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            productRepository.observeProducts()
                .catch { e ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = e.message)
                    }
                    _homeUiEvent.send(HomeUiEvent.ShowMessage(e.message ?: "Loading error"))
                }.collect { products ->
                    _uiState.update { it.copy(products = products, isLoading = false) }
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onFavoriteClick(product: Product) {
        viewModelScope.launch {
            val updatedStatus = !product.isFavorite
            val result = productRepository.toggleFavorite(product.id, updatedStatus)
            if (result.isFailure) {
                _homeUiEvent.send(
                    HomeUiEvent.ShowMessage(
                        result.exceptionOrNull()?.message ?: "Failed to update favorite"
                    )
                )
            }
        }
    }
}