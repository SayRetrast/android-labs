package com.example.labseven

sealed class Screen(val route: String) {
    object Gallery: Screen("gallery_screen")
    object Favourite: Screen("favourite_screen")
}