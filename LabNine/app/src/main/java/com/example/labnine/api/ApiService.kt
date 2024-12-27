package com.example.labnine.api

import com.example.labnine.db.Movie
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
        @Query("y") year: String,
    ): Response<Movie>
}

data class SearchMoviesResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)
