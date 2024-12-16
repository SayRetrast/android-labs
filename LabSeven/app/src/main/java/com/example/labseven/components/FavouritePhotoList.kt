package com.example.labseven.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.labseven.Photo
import com.example.labseven.checkIfIsFavourite
import com.example.labseven.db.PhotoDao
import com.example.labseven.db.PhotoEntity

@Composable
fun FavouritePhotoList(favouritePhotos: List<PhotoEntity>, photoDao: PhotoDao) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(favouritePhotos) {photo ->
            val photoObj = Photo(id = photo.id, title = photo.title, url_sq = photo.url_sq)
            PhotoItem(photoDao = photoDao, photo = photoObj, isFavourite = checkIfIsFavourite(favoritePhotos = favouritePhotos, photo = photoObj))
        }
    }
}

