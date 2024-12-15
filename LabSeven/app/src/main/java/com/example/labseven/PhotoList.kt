package com.example.labseven

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun PhotoList(viewModel: MainViewModel) {
    val fetchedPhotos by viewModel.photos.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchPhotos()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        if (fetchedPhotos.isEmpty()) {
            Text(text = "Loading...")
        } else {
            LazyColumn {
                items(fetchedPhotos) { fetchedPhoto ->
                    Text(text = fetchedPhoto.title)
                    HorizontalDivider()
                }
            }
        }
    }
}