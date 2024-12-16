package com.example.labseven.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labseven.Screen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Gallery.route) {
        composable(route = Screen.Gallery.route) {
            GalleryScreen(navController)
        }

        composable(
            route = Screen.Favourite.route
        ) {
            FavouriteScreen(navController)
        }
    }
}