package com.example.labnine.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.labnine.Screen
import com.example.labnine.api.Movie
import com.example.labnine.viewModels.SearchMovieViewModel
import com.example.labnine.viewModels.SearchMoviesViewModel

@Composable
fun FoundMoviesList(contentPadding: PaddingValues, navController: NavController) {
    val viewModel: SearchMoviesViewModel = viewModel()
    val foundMovies = viewModel.foundMovies.value

    if (foundMovies.isEmpty()) {
        Text(text = "Loading...")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = contentPadding,
        ) {
            items(foundMovies) { movie ->
                MovieItem(movie = movie, navController = navController)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, navController: NavController) {
    val searchMovieViewModel: SearchMovieViewModel = viewModel()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                searchMovieViewModel.setMovie(movie)
                navController.navigate(Screen.AddMovie.createRoute(movie))
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = movie.Poster,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movie.Title,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row {
                    Text(
                        text = movie.Year,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = movie.Type,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
//