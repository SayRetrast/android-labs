package com.example.labnine.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.example.labnine.Screen
import com.example.labnine.viewModels.SearchMoviesViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMoviesScreen(navController: NavController) {
    val searchMoviesViewModel: SearchMoviesViewModel = viewModel()

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Search...") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            searchMoviesViewModel.findMovies(searchText)
                            Log.d( "Debuggg", searchText)
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
                },
                actions = {
                    IconButton(onClick = { navController.navigate(route = Screen.MovieGallery.route) }) {
                        Icon(Icons.Filled.Home, contentDescription = "Gallery")
                    }
                }
            )
        }
    ) { paddingValues ->
        FoundMoviesList(contentPadding = paddingValues, navController = navController)
    }
}
//