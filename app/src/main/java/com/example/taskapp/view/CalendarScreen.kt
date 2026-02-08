package com.example.taskapp.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(
    vm: TaskViewModel
) {
    val tasks by vm.tasks.collectAsState()

    var selectedTask by remember { mutableStateOf<Task?>(null) }

    val grouped = tasks
        .sortedBy { it.dueDate }
        .groupBy { it.dueDate }

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text("Calendar", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))
        }

        grouped.forEach { (date, dayTasks) ->
            item {
                Text(date.toString(), style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(6.dp))
            }

            items(dayTasks, key = { it.id }) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedTask = task }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(task.title, style = MaterialTheme.typography.titleMedium)
                        if (task.description.isNotBlank()) {
                            Text(task.description, style = MaterialTheme.typography.bodySmall)
                        }
                        Text(
                            if (task.done) "Done" else "Not done",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            item {
                Spacer(Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(Modifier.height(8.dp))
            }
        }
    }

    selectedTask?.let { t ->
        TaskDialog(
            task = t,
            onDismiss = { selectedTask = null },
            onSave = { title, desc, due ->
                vm.updateTask(t.copy(title = title, description = desc, dueDate = due))
                selectedTask = null
            },
            onDelete = {
                vm.removeTask(t.id)
                selectedTask = null
            }
        )
    }
}
