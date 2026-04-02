package com.jeywoods.todolistandroid.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeywoods.todolistandroid.viewModel.TaskViewModel

@Composable
fun AddTaskScreen(
    taskViewModel: TaskViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(taskViewModel.errorMessage) {
        taskViewModel.errorMessage?.let {
            snackBarHostState.showSnackbar(it)
            taskViewModel.clearError()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1c95fb))
                            .statusBarsPadding(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
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
                    .padding(paddingValues)
            ) {

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        if (it.length <= 25) {
                            title = it
                            if (it.isNotBlank()) {
                                taskViewModel.clearError()
                            }
                        }
                    },
                    label = { Text("Title") },
                    placeholder = { Text("Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textStyle = MaterialTheme.typography.headlineMedium,
                    singleLine = true,
                    isError = taskViewModel.errorMessage != null && title.isBlank()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { if (it.length <= 150) description = it },
                    label = { Text("Description") },
                    placeholder = { Text("Enter your TODO here") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    textStyle = MaterialTheme.typography.headlineSmall,
                    maxLines = 5
                )

                FloatingActionButton(
                    onClick = {
                        val success = taskViewModel.addTask(title, description)
                        if (success) {
                            onBack()
                        }
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
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .padding(20.dp),
            snackbar = {data ->
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    containerColor = Color.Black,
                    contentColor = Color.White
                ){
                    Text(text = data.visuals.message)
                }
            }
        )
    }
}

