package com.example.labseven

import retrofit2.http.GET

interface ApiService {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=6561003dd145624ad45fd196d3bbc37a&format=json&nojsoncallback=1")
    suspend fun getPhotos(): List<Photo>
}

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val farm: Int,
    val ispublic: Int,
    val isfriend: Int,
    val isfamily: Int,
)