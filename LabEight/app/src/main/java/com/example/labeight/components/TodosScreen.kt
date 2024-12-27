package com.example.labeight.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.labeight.Screen
import com.example.labeight.db.db

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosScreen(navController: NavController) {
    val taskDao = db.taskDao()
    val tasks = taskDao.getAll()

    Scaffold(
        topBar = {
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
    ) { paddingValues ->
        TaskList(contentPadding = paddingValues, tasks = tasks)
    }
}