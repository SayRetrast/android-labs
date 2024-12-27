package com.example.labnine.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") searchText: String,
        @Query("apikey") apiKey: String = "3ac6d38",
    ): Response<SearchMoviesResponse>

    @GET("/")
    suspend fun getMovie(
        @Query("apikey") apiKey: String = "3ac6d38",
        @Query("t") searchText: String,
    ): Response<SearchMoviesResponse>
}

data class SearchMoviesResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)

data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)
