package com.example.taskapp.ui.theme

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskapp.domain.Task
import com.example.taskapp.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    vm: TaskViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") } // YYYY-MM-DD
    var error by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Task App (Week 2)", style = MaterialTheme.typography.headlineMedium)
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
                value = dueDate,
                onValueChange = { dueDate = it },
                label = { Text("Due date (YYYY-MM-DD)") },
                singleLine = true
            )
        }

        item {
            if (error != null) {
                Text(error!!, color = MaterialTheme.colorScheme.error)
            }
        }

        item {
            Button(onClick = {
                val ok = vm.addTaskFromInputs(title, description, dueDate)
                if (!ok) {
                    error = "Syötä title + due date muodossa YYYY-MM-DD (esim. 2026-01-25)"
                    return@Button
                }
                error = null
                title = ""
                description = ""
                dueDate = ""
            }) {
                Text("Add task")
            }
        }

        item { Spacer(Modifier.height(8.dp)) }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { vm.filterByDone(true) }) { Text("Done") }
                Button(onClick = { vm.filterByDone(false) }) { Text("Not done") }
                Button(onClick = { vm.showAll() }) { Text("All") }
            }
        }

        item {
            Button(onClick = { vm.sortByDueDate() }) {
                Text("Sort by date")
            }
        }

        item {
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
        }

        items(vm.tasks, key = { it.id }) { task ->
            TaskRow(
                task = task,
                onToggle = { vm.toggleDone(task.id) },
                onDelete = { vm.removeTask(task.id) }
            )
            HorizontalDivider()
        }
    }
}

@Composable
private fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Checkbox(
            checked = task.done,
            onCheckedChange = { onToggle() }
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(task.title, style = MaterialTheme.typography.titleMedium)
            if (task.description.isNotBlank()) {
                Text(task.description, style = MaterialTheme.typography.bodySmall)
            }
            Text("due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
        }

        Button(onClick = onDelete) { Text("Delete") }
    }
}
