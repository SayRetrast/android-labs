package com.example.labseven.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labseven.checkIfIsFavourite
import com.example.labseven.db.PhotoDao
import com.example.labseven.db.PhotoEntity
import com.example.labseven.viewModels.PhotosViewModel

@Composable
fun PhotoList(photoDao: PhotoDao, favoritePhotos: List<PhotoEntity>, contentPadding: PaddingValues) {
    val viewModel: PhotosViewModel = viewModel()
    val photos = viewModel.photos.value

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = contentPadding,
    ) {
        items(photos) { photo ->
            PhotoItem(photoDao = photoDao, photo = photo, isFavourite = checkIfIsFavourite(favoritePhotos, photo))
        }
    }
}