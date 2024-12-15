package com.example.labseven

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    fun fetchPhotos() {
        viewModelScope.launch {
            try {
                val response = apiService.getPhotos()
                _photos.value = response
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}