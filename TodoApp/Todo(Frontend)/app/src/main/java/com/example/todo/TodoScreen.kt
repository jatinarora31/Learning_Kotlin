package com.example.todo

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(navController: NavController, viewModel: TodoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val todos = viewModel.todos

//    val todos = List(30) {
//        Todo("Task $it", "Description for task $it")
//    }

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Todo",
                        modifier = Modifier.padding(start = 12.dp).size(45.dp),
                        tint = Color.White
                    )
                },
                title = { Text("My Todo List",
                    modifier = Modifier.padding(start=13.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )},
                actions = {
                    IconButton(
                        onClick = { viewModel.fetchTodos() }
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu",
                            Modifier.size(32.dp)
                        )
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false })
                    {
                        DropdownMenuItem(text = {Text("Completed task")},
                            onClick = { expanded = false})
                    }
                },


                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0072ae),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate("add_todo")}) {
                Icon(imageVector = Icons.Default.AddCircle,
                    contentDescription = "Add Todo",
                    modifier = Modifier.size(35.dp))
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
        ) {
            item { Spacer(modifier = Modifier.height(15.dp)) }
            items(todos) { todo -> TodoItem(todo = todo, onCheckedChange = {})}
        }

    }

}

@Composable
fun TodoItem(todo: Todo, onCheckedChange: (Boolean) -> Unit) {

    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFececec)
        )

    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = todo.isCompleted,
                onCheckedChange = onCheckedChange)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = if (todo.isCompleted)
                        TextDecoration.LineThrough else null)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = todo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}