package com.example.labnine.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labnine.Screen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MovieGallery.route) {
        composable(route = Screen.MovieGallery.route) {
            MovieGalleryScreen(navController)
        }

        composable(
            route = Screen.SearchMovies.route
        ) {
            SearchMoviesScreen(navController)
        }
    }
}