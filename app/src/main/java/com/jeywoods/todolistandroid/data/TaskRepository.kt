package com.jeywoods.todolistandroid.data

import com.jeywoods.todolistandroid.model.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    val tasks: Flow<List<TaskEntity>> = dao.getAllTasks()

    suspend fun addTask(task: TaskEntity) {
        dao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        dao.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        dao.deleteTask(task)
    }
    fun findTaskById(id: String): Flow<TaskEntity?>{
        return dao.getTaskById(id)
    }
}