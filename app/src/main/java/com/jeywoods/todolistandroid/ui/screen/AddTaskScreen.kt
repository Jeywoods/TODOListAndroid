package com.jeywoods.todolistandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeywoods.todolistandroid.viewModel.TaskViewModel

@Composable
fun AddTaskScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit,
    onAddClick: (title: String, description: String) -> Unit
){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1c95fb))
                        .statusBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.padding(start = 10.dp)
                    ){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "New task",
                        modifier = Modifier.padding(start = 20.dp),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )

                }

            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top
        ){
            OutlinedTextField(
                value = title,
                onValueChange = { if(it.length <= 25) title = it },
                label = { Text("Title") },
                placeholder = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textStyle = MaterialTheme.typography.headlineMedium,
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { if(it.length <= 150) description = it },
                label = { Text("Description")},
                placeholder = { Text("Enter your TODO here") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                textStyle = MaterialTheme.typography.headlineSmall
            )
            FloatingActionButton(
                onClick = {
                    onAddClick(title, description)
                    onBack()
                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(56.dp)
                    .align(Alignment.End),
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Add task"
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    val taskViewModel = remember { TaskViewModel() }
    MaterialTheme {
        AddTaskScreen(
            taskViewModel = taskViewModel,
            onBack = {},
            onAddClick = { title, description ->  }
        )
    }
}

