package com.example.labseven.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labseven.checkIfIsFavourite
import com.example.labseven.db.PhotoDao
import com.example.labseven.db.PhotoEntity
import com.example.labseven.viewModels.SearchViewModel

@Composable
fun SearchPhotoList(photoDao: PhotoDao, favoritePhotos: List<PhotoEntity>) {
    val viewModel: SearchViewModel = viewModel()
    val foundPhotos = viewModel.foundPhotos.value

    if (foundPhotos.isEmpty()) {
        Text(text = "Loading...")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(foundPhotos) {photo ->
                PhotoItem(photoDao = photoDao, photo = photo, isFavourite = checkIfIsFavourite(favoritePhotos, photo))
            }
        }
    }
}