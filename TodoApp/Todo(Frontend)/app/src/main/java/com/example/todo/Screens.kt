package com.example.todo

sealed class Screens(val route: String) {

    object TodoList : Screens("todo_list")
    object AddTodo : Screens("add_todo")

}