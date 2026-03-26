package com.jeywoods.todolistandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeywoods.todolistandroid.model.Task
import com.jeywoods.todolistandroid.components.TaskItem
import com.jeywoods.todolistandroid.viewModel.TaskViewModel

@Composable
fun ToDoScreen(
    taskViewModel: TaskViewModel,
    onAddClick: () -> Unit,
    onTaskClick: (String) -> Unit
) {
    val tasks = taskViewModel.tasks

    Scaffold(
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1c95fb))
                        .statusBarsPadding(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "To do list",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White
                    )

                    Text(
                        text = "Total: ${tasks.size} - Checked: ${tasks.count { it.isChecked }}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp),
                        color = Color.White
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick() },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .height(40.dp)
                    .shadow(2.dp),
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                Text(
                    text = "Add",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 70.dp)
        ) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onDelete = { taskViewModel.deleteTask(task) },
                    onChecked = { checked -> taskViewModel.toggleTask(task, checked) },
                    onClick = { onTaskClick(task.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoScreenPreview() {
    val taskViewModel = remember { TaskViewModel() }
    ToDoScreen(
        taskViewModel = taskViewModel,
        onAddClick = {},
        onTaskClick = {}
    )
}