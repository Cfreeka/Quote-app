package com.quotes.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home_Screen") {
        composable(route = "Home_Screen") {
            HomeScreen(navController)
        }
        composable(route = "Favourite_Screen") {
            FavouriteScreen(navController)
        }
    }
}