package com.example.labseven.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.labseven.Screen
import com.example.labseven.db.db


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(navController: NavController) {
    val photoDao = db.photoDao()
    val favouritePhotos = photoDao.getAll()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Favorite photos")
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = Screen.Gallery.route) }) {
                        Icon(Icons.Filled.Home, contentDescription = "Gallery Screen")
                    }
                    IconButton(onClick = {
                        photoDao.deleteAll()
                        navController.navigate(route = Screen.Favourite.route)
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Favourite Photos")
                    }
                }
            )
        }
    ) { paddingValues ->
        FavouritePhotoList(
            favouritePhotos = favouritePhotos,
            photoDao = photoDao,
            contentPadding = paddingValues
        )
    }
}
