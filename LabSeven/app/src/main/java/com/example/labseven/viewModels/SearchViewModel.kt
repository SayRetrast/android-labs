package com.example.labseven.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labseven.Photo
import com.example.labseven.RetrofitInstance
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    val foundPhotos = mutableStateOf<List<Photo>>(listOf())

    init {
        findPhotos("")
    }

    fun findPhotos(searchText: String) {
        viewModelScope.launch {
            try {
                val response = apiService.searchPhotos(searchText = searchText)

                if (response.isSuccessful) {
                    foundPhotos.value = response.body()?.photos?.photo!!
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }

    fun clearFoundPhotos() {
        foundPhotos.value = listOf()
    }
}