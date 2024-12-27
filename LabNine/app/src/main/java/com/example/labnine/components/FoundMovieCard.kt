package com.example.labnine.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.labnine.db.db
import com.example.labnine.viewModels.SearchMovieViewModel

@Composable
fun FoundMovieCard() {
    val movieDao = db.movieDao()

    val viewModel: SearchMovieViewModel = viewModel()

    val movie = viewModel.foundMovie.value
    val isMovieFound = viewModel.isMovieFound.value

    if (!isMovieFound) {
        Text(text = "")
    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = movie.Poster,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = movie.Title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

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

            if (movie.Genre != null) {
                Text(
                    text = movie.Genre,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Button(
                onClick = { movieDao.insert(movie) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Movie")
            }
        }
    }
}
//