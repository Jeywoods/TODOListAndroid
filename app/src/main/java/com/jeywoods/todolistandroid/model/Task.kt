package com.jeywoods.todolistandroid.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String = "",
    val isChecked: Boolean = false
)