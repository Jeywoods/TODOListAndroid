package com.jeywoods.todolistandroid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeywoods.todolistandroid.data.TaskDatabase
import com.jeywoods.todolistandroid.data.TaskRepository
import com.jeywoods.todolistandroid.ui.screen.AddTaskScreen
import com.jeywoods.todolistandroid.ui.screen.DetailsTaskScreen
import com.jeywoods.todolistandroid.ui.screen.ToDoScreen
import com.jeywoods.todolistandroid.viewModel.TaskViewModel
import com.jeywoods.todolistandroid.viewModel.TaskViewModelFactory


@Composable
fun Navigation() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val repository = TaskRepository(TaskDatabase.getDatabase(context).taskDao())

    val taskViewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(repository)
    )

    NavHost(navController = navController, startDestination = "ToDoScreen") {
        composable("ToDoScreen") {
            ToDoScreen(
                taskViewModel = taskViewModel,
                onAddClick = { navController.navigate("AddTaskScreen") },
                onTaskClick = { taskId ->
                    navController.navigate("DetailsTaskScreen/$taskId")
                }
            )
        }

        composable("AddTaskScreen") {
            AddTaskScreen(
                taskViewModel = taskViewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable("DetailsTaskScreen/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId") ?: ""
            val taskFlow = taskViewModel.getTaskById(taskId)
            val task by taskFlow.collectAsState(initial = null)

            task?.let {
                DetailsTaskScreen(
                    task = it,
                    taskViewModel = taskViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}