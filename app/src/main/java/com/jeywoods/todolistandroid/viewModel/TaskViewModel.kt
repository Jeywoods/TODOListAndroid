package com.jeywoods.todolistandroid.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jeywoods.todolistandroid.model.Task

class TaskViewModel : ViewModel() {
    var tasks = mutableStateListOf<Task>()
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun addTask(title: String, description: String): Boolean {
        if (title.isBlank()) {
            errorMessage = "Title cannot be empty"
            return false
        }
        errorMessage = null
        tasks.add(Task(title = title, description = description))
        return true
    }

    fun deleteTask(task: Task) {
        tasks.remove(task)
        errorMessage = null
    }

    fun toggleTask(task: Task, checked: Boolean) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(isChecked = checked)
        }
    }

    fun getTaskById(id: String): Task? {
        return tasks.find { it.id == id }
    }

    fun updateTask(task: Task, newTitle: String, newDescription: String): Boolean {
        if (newTitle.isBlank()) {
            errorMessage = "Title cannot be empty"
            return false
        }
        errorMessage = null
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(
                title = newTitle,
                description = newDescription
            )
            return true
        }
        return false
    }
    fun clearError() {
        errorMessage = null
    }
}