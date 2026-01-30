package com.example.taskapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel
import java.time.LocalDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    vm: TaskViewModel = viewModel()
) {
    val tasks by vm.tasks.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDateText by remember { mutableStateOf("") } // YYYY-MM-DD
    var error by remember { mutableStateOf<String?>(null) }

    var selectedTask by remember { mutableStateOf<Task?>(null) }

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Task App (Week 3)", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(12.dp))
        }

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true
            )
        }

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") }
            )
        }

        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = dueDateText,
                onValueChange = { dueDateText = it },
                label = { Text("Due date (YYYY-MM-DD)") },
                singleLine = true
            )
        }

        item {
            if (error != null) Text(error!!)
        }

        item {
            Button(onClick = {
                val due = try { LocalDate.parse(dueDateText.trim()) } catch (e: Exception) { null }
                if (title.trim().isEmpty() || due == null) {
                    error = "Syötä title ja päivämäärä muodossa YYYY-MM-DD"
                    return@Button
                }
                vm.addTask(title, description, due)
                title = ""
                description = ""
                dueDateText = ""
                error = null
            }) {
                Text("Add task")
            }
        }

        item {
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
        }

        // List
        items(tasks, key = { it.id }) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Checkbox(
                    checked = task.done,
                    onCheckedChange = { vm.toggleDone(task.id) }
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(task.title, style = MaterialTheme.typography.titleMedium)
                    if (task.description.isNotBlank()) {
                        Text(task.description, style = MaterialTheme.typography.bodySmall)
                    }
                    Text("due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
                }

                Button(onClick = { selectedTask = task }) {
                    Text("Edit")
                }
            }

            HorizontalDivider()
        }
    }

    selectedTask?.let { task ->
        DetailDialog(
            task = task,
            onDismiss = { selectedTask = null },
            onSave = { updated ->
                vm.updateTask(updated)
                selectedTask = null
            },
            onDelete = {
                vm.removeTask(task.id)
                selectedTask = null
            }
        )
    }
}
