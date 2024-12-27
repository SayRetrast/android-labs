package com.example.labeight.components

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.labeight.Screen
import com.example.labeight.db.Task
import com.example.labeight.db.db

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodosScreen(navController: NavController) {
    val taskDao = db.taskDao()
    val tasks = remember { mutableStateListOf(*taskDao.getAll().toTypedArray()) }

    val onDeleteTask: (Task) -> Unit = { task ->
        taskDao.delete(task)
        tasks.remove(task)
    }

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
        TaskList(contentPadding = paddingValues, tasks = tasks, onDeleteTask = onDeleteTask)
    }
}