package com.example.labnine

import com.example.labnine.db.Movie
import com.google.gson.Gson
import java.net.URLEncoder

fun Movie.toJson(): String {
    return Gson().toJson(this)
}

fun String.toMovie(): Movie {
    return Gson().fromJson(this, Movie::class.java)
}

sealed class Screen(val route: String) {
    object SearchMovies: Screen("search_movies")
    object MovieGallery: Screen("movie_gallery")
    object AddMovie : Screen("add_movie?movieJson={movieJson}") {
        fun createRoute(movie: Movie? = null): String {
            val movieJson = movie?.toJson() ?: ""
            val encodedMovieJson = URLEncoder.encode(movieJson, "UTF-8")
            return "add_movie?movieJson=$encodedMovieJson"
        }
    }
}
//