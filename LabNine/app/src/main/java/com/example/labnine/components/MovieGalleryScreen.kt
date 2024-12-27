package com.example.labnine.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.labnine.Screen
import com.example.labnine.db.Movie
import com.example.labnine.db.db


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGalleryScreen(navController: NavController) {
    val movieDao = db.movieDao()
    val movies = remember { mutableStateListOf<Movie>() }
    val checkedMovies = remember { mutableStateOf<Set<String>>(emptySet()) }

    LaunchedEffect(Unit) {
        movies.addAll(movieDao.getAll())
    }

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
                    IconButton(
                        onClick = {
                            movieDao.deleteMoviesByIds(checkedMovies.value.toList())
                            checkedMovies.value = emptySet()

                            movies.clear()
                            movies.addAll(movieDao.getAll())
                        },
                        enabled = checkedMovies.value.isNotEmpty()
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete selected movies")
                    }
                }
            )
        }
    ) { paddingValues ->
        SavedMoviesList(contentPadding = paddingValues, movies = movies, checkedMovies = checkedMovies)
    }
}
