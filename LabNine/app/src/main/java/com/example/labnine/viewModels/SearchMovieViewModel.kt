package com.example.labnine.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labnine.api.RetrofitInstance
import com.example.labnine.db.Movie
import kotlinx.coroutines.launch

class SearchMovieViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    val isMovieFound = mutableStateOf(false)
    val foundMovie = mutableStateOf(Movie("", "", "", "", "", ""))

    fun findMovie(searchText: String, year: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getMovie(searchText = searchText, year = year)

                if (response.isSuccessful) {
                    foundMovie.value = response.body()!!
                    isMovieFound.value = true
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }

    fun setMovie(movie: Movie) {
        foundMovie.value = movie
        isMovieFound.value = true
    }
}
//