package com.jeywoods.todolistandroid.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeywoods.todolistandroid.model.TaskEntity

@Composable
fun TaskItem(
    task: TaskEntity,
    onDelete: () -> Unit = {},
    onChecked: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = task.isChecked,
            onCheckedChange = { checked ->
                onChecked(checked)
            }
        )

        Text(
            text = task.title,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = if (task.isChecked) {
                TextDecoration.LineThrough
            } else {
                TextDecoration.None
            }
        )

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete task"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    MaterialTheme {
        TaskItem(
            task = TaskEntity(
                id = "1",
                title = "Sample task",
                isChecked = false
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemCheckedPreview() {
    MaterialTheme {
        TaskItem(
            task = TaskEntity(
                id = "2",
                title = "Completed task",
                isChecked = true
            )
        )
    }
}