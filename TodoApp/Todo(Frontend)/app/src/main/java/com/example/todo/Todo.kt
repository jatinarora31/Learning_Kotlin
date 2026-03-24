package com.example.todo

data class Todo(val id:Int, val title:String, val description:String, val is_completed: Boolean) {

    val isCompleted: Boolean
        get() = is_completed

}