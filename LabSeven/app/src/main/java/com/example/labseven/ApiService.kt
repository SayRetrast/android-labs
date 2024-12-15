package com.example.labseven

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("services/rest/")
    suspend fun getPhotos(
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("api_key") apiKey: String = "6561003dd145624ad45fd196d3bbc37a",
        @Query("extras") extras: String = "url_sq",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): Response<PhotosResponse>
}

data class PhotosResponse(
    val photos: Photos
)

data class Photos(
    val photo: List<Photo>
)

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val url_sq: String
)