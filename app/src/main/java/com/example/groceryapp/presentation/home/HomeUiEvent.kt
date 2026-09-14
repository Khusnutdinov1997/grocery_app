package com.example.groceryapp.presentation.home

sealed interface HomeUiEvent {
    data class ShowMessage(
        val message: String
    ) : HomeUiEvent
}