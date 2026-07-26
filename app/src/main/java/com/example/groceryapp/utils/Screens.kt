package com.example.groceryapp.utils

sealed class Screens(val route: String) {
    object Splash1: Screens(route = "splash1")
    object Splash2 : Screens(route = "splash2")
}