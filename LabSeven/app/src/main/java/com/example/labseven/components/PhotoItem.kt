package com.example.labseven.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.labseven.Photo
import com.example.labseven.db.PhotoDao
import com.example.labseven.db.PhotoEntity

@Composable
fun PhotoItem(photoDao: PhotoDao, photo: Photo, isFavourite: Boolean) {
    var isFavouriteState by remember { mutableStateOf(isFavourite) }

    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        AsyncImage(
            model = photo.url_sq,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().size(120.dp),
        )
        Text(text = photo.title, color = Color(0, 0, 0))
        Button(
            onClick = {
                if (!isFavouriteState) {
                    photoDao.insert(PhotoEntity(id = photo.id, title = photo.title, url_sq = photo.url_sq))
                } else {
                    photoDao.delete(PhotoEntity(id = photo.id, title = photo.title, url_sq = photo.url_sq))
                }

                isFavouriteState = !isFavouriteState
            },
            modifier = Modifier.fillMaxWidth().background(Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                imageVector = if (!isFavouriteState) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}