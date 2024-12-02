package com.example.bmicalc

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Results : NavRoutes("results")
}