package com.jeywoods.todolistandroid.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeywoods.todolistandroid.data.TaskRepository
import com.jeywoods.todolistandroid.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    var tasks: StateFlow<List<TaskEntity>> = repository.tasks
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun addTask(title: String, description: String): Boolean {
        if (title.isBlank()) {
            errorMessage = "Title cannot be empty"
            return false
        }
        errorMessage = null
        viewModelScope.launch {
            repository.addTask(TaskEntity(title = title, description = description))
        }
        return true
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
        errorMessage = null
    }

    fun toggleTask(task: TaskEntity, checked: Boolean) {
        viewModelScope.launch { repository.updateTask(task.copy(isChecked = checked)) }
    }
    fun updateTask(task: TaskEntity, newTitle: String, newDescription: String) {
        if (newTitle.isBlank()) {
            errorMessage = "Title cannot be empty"
            return
        }
        errorMessage = null
        viewModelScope.launch {
            repository.updateTask(task.copy(title = newTitle, description = newDescription))
        }
    }
    fun getTaskById(id: String): Flow<TaskEntity?> {
        return repository.findTaskById(id)
    }
    fun clearError() {
        errorMessage = null
    }
}