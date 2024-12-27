package com.example.labnine.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labnine.api.Movie
import com.example.labnine.api.RetrofitInstance
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
                    Log.d( "Debuggg", "HERE")
                    Log.d( "Debuggg", foundMovie.value.Title)
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}