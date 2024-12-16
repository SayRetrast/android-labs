package com.example.labseven

import com.example.labseven.db.PhotoEntity

fun checkIfIsFavourite(favoritePhotos: List<PhotoEntity>, photo: Photo): Boolean {
    for (favouritePhoto in favoritePhotos) {
        if (favouritePhoto.id == photo.id) return true
    }

    return false
}