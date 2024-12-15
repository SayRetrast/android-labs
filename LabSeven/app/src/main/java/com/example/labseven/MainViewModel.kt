package com.example.labseven

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    val photos = mutableStateOf<List<Photo>>(listOf())

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        viewModelScope.launch {
            try {
                val response = apiService.getPhotos()

                if (response.isSuccessful) {
                    photos.value = response.body()?.photos?.photo!!
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}