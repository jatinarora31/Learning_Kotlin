package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composecalculator.ui.theme.ComposeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel : TodoViewModel = viewModel()
            TodoScreen(viewModel)}
    }
}

data class Todo(val id:Int, val title: String) {
}

@Composable
fun TodoItem(todo: Todo) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) { Text(text = todo.title, modifier = Modifier.padding(16.dp)) }
}
@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(45.dp)) {
        TextField(value = text, onValueChange = {text = it}, label = {Text("Enter Todo")}, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { viewModel.addTodo(text);text = "" } , modifier = Modifier.fillMaxWidth()) {
            Text("Add Todo")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn{ items(viewModel.todos) {todo -> TodoItem(todo) } }
    }
}

class TodoViewModel : ViewModel() {
    private val _todos = mutableStateListOf<Todo>()
    val todos : List<Todo> = _todos

    private var currentId = 0

    fun addTodo(title: String) {
        _todos.add(Todo(currentId++, title))
    }

}