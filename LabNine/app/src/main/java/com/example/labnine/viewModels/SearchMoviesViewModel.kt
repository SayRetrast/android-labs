package com.example.labnine.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labnine.api.Movie
import com.example.labnine.api.RetrofitInstance
import kotlinx.coroutines.launch

class SearchMoviesViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    val foundMovies = mutableStateOf<List<Movie>>(listOf())

    init {
        findMovies("")
    }

    fun findMovies(searchText: String) {
        viewModelScope.launch {
            try {
                val response = apiService.searchMovies(searchText = searchText)

                if (response.isSuccessful) {
                    foundMovies.value = response.body()?.Search!!
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}
//