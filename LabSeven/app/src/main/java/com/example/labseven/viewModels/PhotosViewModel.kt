package com.example.labseven.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labseven.Photo
import com.example.labseven.RetrofitInstance
import kotlinx.coroutines.launch

class PhotosViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    val photos = mutableStateOf<List<Photo>>(listOf())
//    val newPhoto = mutableStateOf<Photo>(Photo(
//        "",
//        "",
//        "",
//        "",
//        0,
//        "",
//        ""
//    ))

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

    fun clearPhotos() {
        photos.value = listOf()
    }
}