package com.example.groceryapp.utils

sealed class Screens(val route: String) {
    object Splash1: Screens(route = "splash1")
    object Splash2 : Screens(route = "splash2")

    object RegistrationScreen1: Screens(route = "registration1")
    object RegistrationScreen2: Screens(route = "registration2")
    object RegistrationScreen3: Screens(route = "registration3")

    object Home: Screens(route = "home")
    object ProductDetail: Screens(route = "product/{productId}"){
        const val ARG_PRODUCT_ID = "productId"
        fun createRoute(productId: String) = "product/$productId"
    }

    object Favorite: Screens(route = "favorite")
    object Profile: Screens("auth_graph")
    object Cart: Screens("cart")



}