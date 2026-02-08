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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    vm: TaskViewModel
) {
    val tasks by vm.tasks.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Home (Task list)", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))

            Button(onClick = { showAddDialog = true }) {
                Text("+ Add task")
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
        }

        items(tasks, key = { it.id }) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
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
                    Text("Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
                }

                TextButton(onClick = { selectedTask = task }) {
                    Text("Edit")
                }
            }

            HorizontalDivider()
        }
    }

    // ADD TASK
    if (showAddDialog) {
        TaskDialog(
            task = null,
            onDismiss = { showAddDialog = false },
            onSave = { title, desc, due ->
                vm.addTask(title, desc, due)
                showAddDialog = false
            }
        )
    }

    // EDIT TASK
    selectedTask?.let { t ->
        TaskDialog(
            task = t,
            onDismiss = { selectedTask = null },
            onSave = { title, desc, due ->
                vm.updateTask(
                    t.copy(
                        title = title,
                        description = desc,
                        dueDate = due
                    )
                )
                selectedTask = null
            },
            onDelete = {
                vm.removeTask(t.id)
                selectedTask = null
            }
        )
    }
}
