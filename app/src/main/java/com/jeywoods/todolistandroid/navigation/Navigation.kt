package com.jeywoods.todolistandroid.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeywoods.todolistandroid.ui.screen.AddTaskScreen
import com.jeywoods.todolistandroid.ui.screen.DetailsTaskScreen
import com.jeywoods.todolistandroid.ui.screen.ToDoScreen
import com.jeywoods.todolistandroid.viewModel.TaskViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

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
            val task = taskViewModel.getTaskById(taskId)
            if (task != null) {
                DetailsTaskScreen(
                    task = task,
                    taskViewModel = taskViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}