package com.example.labnine

sealed class Screen(val route: String) {
    object SearchMovies: Screen("search_movies")
    object MovieGallery: Screen("movie_gallery")
    object AddMovie: Screen("add_movie")
}