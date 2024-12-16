package com.example.labeight.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.labeight.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosScreen(navController: NavController) {
    Box {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(text = "Todos")
            },
            actions = {
                IconButton(onClick = { navController.navigate(route = Screen.AddTodo.route) }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add todo button")
                }
            }
        )
    }
}