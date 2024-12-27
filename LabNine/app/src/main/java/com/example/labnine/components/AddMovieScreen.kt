package com.example.labnine.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.labnine.Screen
import com.example.labnine.viewModels.SearchMovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreen(navController: NavController) {
    val viewModel: SearchMovieViewModel = viewModel()

    val movieTitleState = remember { mutableStateOf("") }
    val movieYearState = remember { mutableStateOf("") }

    val foundMovie = viewModel.foundMovie.value
    val isMovieFound = viewModel.isMovieFound.value

    LaunchedEffect(foundMovie) {
        if (isMovieFound) {
            movieTitleState.value = foundMovie.Title
            movieYearState.value = foundMovie.Year
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Add Movie")
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = Screen.MovieGallery.route) }) {
                        Icon(Icons.Filled.Home, contentDescription = "Gallery")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = movieTitleState.value,
                onValueChange = { movieTitleState.value = it },
                label = { Text("Movie Title") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = movieYearState.value,
                onValueChange = { movieYearState.value = it },
                label = { Text("Release Year") },
                modifier = Modifier.fillMaxWidth()
            )

            Button (
                onClick = {
                    viewModel.findMovie(movieTitleState.value, movieYearState.value)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Search Movie")
            }

            if (isMovieFound) {
                FoundMovieCard()
            }
        }
    }
}

