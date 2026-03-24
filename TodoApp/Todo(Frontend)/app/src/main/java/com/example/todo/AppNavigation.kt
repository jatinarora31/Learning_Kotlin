package com.example.todo

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.TodoList.route)
    {
        composable(Screens.TodoList.route) { TodoScreen(navController) }
        composable(Screens.AddTodo.route) { AddTodoScreen(navController) }
    }
}