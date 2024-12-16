package com.example.labseven.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.labseven.Screen
import com.example.labseven.db.db
import com.example.labseven.viewModels.PhotosViewModel
import com.example.labseven.viewModels.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(navController: NavController) {
    val photoDao = db.photoDao()
    val favouritePhotos = photoDao.getAll()

    val photosViewModel: PhotosViewModel = viewModel()
    val searchViewModel: SearchViewModel = viewModel()

    var isSearchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var isSearched by remember { mutableStateOf(false) }

    Box {
        if(!isSearched) {
            PhotoList(photoDao = photoDao, favoritePhotos = favouritePhotos)
        } else {
            SearchPhotoList(photoDao = photoDao, favoritePhotos = favouritePhotos)
        }

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                if (!isSearchActive) {
                    Text(text = if (!isSearched) "Gallery" else "Searched photos")
                } else {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Search...") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            isSearched = true
                            isSearchActive = false

                            photosViewModel.clearPhotos()
                            searchViewModel.findPhotos(searchText)
                        }),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    isSearchActive = !isSearchActive
                    searchText = ""
                }) {
                    Icon(if(!isSearchActive) {Icons.Filled.Search} else {Icons.Filled.Close}, contentDescription = "Search")
                }

                if (isSearched) {
                    IconButton(onClick = {
                        isSearched = false
                        isSearchActive = false
                        searchText = ""

                        searchViewModel.clearFoundPhotos()
                        photosViewModel.fetchPhotos()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }

                IconButton(onClick = { navController.navigate(route = Screen.Favourite.route) }) {
                    Icon(Icons.Filled.Star, contentDescription = "Favorite Screen")
                }
            }
        )
    }
}