package com.example.labeight.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labeight.Screen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Todos.route) {
        composable(route = Screen.Todos.route) {
            TodosScreen(navController)
        }

        composable(
            route = Screen.AddTodo.route
        ) {
            AddTodoScreen(navController)
        }
    }
}