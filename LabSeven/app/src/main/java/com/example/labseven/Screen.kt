package com.example.labseven

sealed class Screen(val route: String) {
    object Gallery: Screen("gallery")
    object Favourite: Screen("favourite")
}