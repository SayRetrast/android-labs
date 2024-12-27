package com.example.labnine.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import com.example.labnine.Screen
import com.example.labnine.db.db


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGalleryScreen(navController: NavController) {
    val movieDao = db.movieDao()
    val movies = movieDao.getAll()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Gallery")
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = Screen.SearchMovies.route) }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { navController.navigate(Screen.AddMovie.createRoute(null)) }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add movie")
                    }
                }
            )
        }
    ) { paddingValues ->
        SavedMoviesList(contentPadding = paddingValues, navController = navController, movies = movies)
    }
}
//