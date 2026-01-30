package com.example.taskapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskapp.model.Task
import java.time.LocalDate

@Composable
fun DetailDialog(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: () -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var dueDateText by remember { mutableStateOf(task.dueDate.toString()) } // YYYY-MM-DD
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit task") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = dueDateText,
                    onValueChange = { dueDateText = it },
                    label = { Text("Due date (YYYY-MM-DD)") },
                    singleLine = true
                )

                if (error != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(error!!)
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                val due = try { LocalDate.parse(dueDateText.trim()) } catch (e: Exception) { null }
                if (title.trim().isEmpty() || due == null) {
                    error = "imput title and date in YYYY-MM-DD format"
                    return@Button
                }
                onSave(task.copy(title = title.trim(), description = description.trim(), dueDate = due))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDelete) {
                Text("Delete")
            }
        }
    )
}
