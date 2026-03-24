package com.example.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {

    private val repository = TodoRepository()

    var todos = mutableStateListOf<Todo>()
    var todosError by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            isLoading = true
            try {
                todos.clear()
                todosError = null
                val result = repository.getTodos()
                if(result.isSuccess) {
                    todos.addAll(result.getOrDefault(emptyList()))
                } else {
                    todosError = result.exceptionOrNull()?.message
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            isLoading = false
        }
    }

}