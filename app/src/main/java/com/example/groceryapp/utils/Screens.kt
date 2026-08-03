package com.example.groceryapp.utils

sealed class Screens(val route: String) {
    object Splash1: Screens(route = "splash1")
    object Splash2 : Screens(route = "splash2")
    object Home: Screens(route = "home")

    object RegistrationScreen1: Screens(route = "registration1")
    object RegistrationScreen2: Screens(route = "registration2")
    object RegistrationScreen3: Screens(route = "registration3")


}