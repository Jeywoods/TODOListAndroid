package com.jeywoods.todolistandroid.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.jeywoods.todolistandroid.model.Task

class TaskViewModel : ViewModel() {

    var tasks = mutableStateListOf<Task>()
        private set

    fun addTask(title: String, description: String) {
        tasks.add(Task(title = title, description = description))
    }

    fun deleteTask(task: Task) {
        tasks.remove(task)
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

    fun updateTask(task: Task, newTitle: String, newDescription: String) {
        val index = tasks.indexOf(task)
        if (index != -1) {
            tasks[index] = task.copy(
                title = newTitle,
                description = newDescription
            )
        }
    }
}