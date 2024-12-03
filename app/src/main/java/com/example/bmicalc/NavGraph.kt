package com.example.bmicalc

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bmicalc.screens.HomeScreen
import com.example.bmicalc.screens.ResultsScreen

@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = "results/{num1}/{num2}",
            arguments = listOf(
                navArgument(name = "num1") {
                    type = NavType.StringType
                },
                navArgument(name = "num2") {
                    type = NavType.StringType
                }
            )
        ) {backstackEntry ->
            ResultsScreen(
                num1 = backstackEntry.arguments!!.getString("num1"),
                num2 = backstackEntry.arguments!!.getString("num2"),
                onBackClicked = {
                    navController.navigateUp()
                }
            )

        }
    }
}