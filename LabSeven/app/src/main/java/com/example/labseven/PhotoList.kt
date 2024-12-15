package com.example.labseven

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun PhotoList() {
    val viewModel: MainViewModel = viewModel()
    val photos = viewModel.photos.value

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(photos) {photo ->
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                AsyncImage(
                    model = photo.url_sq,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().size(120.dp)
                )
                Text(text = photo.title, color = Color(0, 0, 0))
            }
        }
    }
}