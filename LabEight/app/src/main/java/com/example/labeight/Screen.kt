package com.example.labeight

sealed class Screen(val route: String) {
    object Todos: Screen("todos")
    object AddTodo: Screen("add_todo")
}