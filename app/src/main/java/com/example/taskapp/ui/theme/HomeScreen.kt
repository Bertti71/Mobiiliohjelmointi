package com.example.taskapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskapp.domain.*
import java.time.LocalDate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var tasks by remember { mutableStateOf(TaskRepository.mockTasks) }
    var visibleTasks by remember { mutableStateOf(tasks) }
    var nextId by remember {
        mutableIntStateOf((tasks.maxOfOrNull { it.id } ?: 0) + 1)
    }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Task App (Week 1)",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(12.dp))

        // ADD / REMOVE
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                val newTask = Task(
                    id = nextId++,
                    title = "Uusi task",
                    description = "Lisätty napista",
                    priority = 2,
                    dueDate = LocalDate.now().plusDays(3),
                    done = false
                )
                tasks = addTask(tasks, newTask)
                visibleTasks = tasks
            }) { Text("Add") }

            Button(onClick = {
                tasks = removeFirstTask(tasks)
                visibleTasks = tasks
            }) { Text("Remove 1st") }
        }

        Spacer(Modifier.height(8.dp))

        // FILTERS
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { visibleTasks = filterByDone(tasks, true) }) { Text("Done") }
            Button(onClick = { visibleTasks = filterByDone(tasks, false) }) { Text("Not done") }
            Button(onClick = { visibleTasks = tasks }) { Text("All") }
        }

        Spacer(Modifier.height(8.dp))

        Button(onClick = { visibleTasks = sortByDueDate(visibleTasks) }) {
            Text("Sort by date")
        }

        Spacer(Modifier.height(12.dp))
        HorizontalDivider()
        Spacer(Modifier.height(12.dp))

        // TASK LIST
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            visibleTasks.forEach { task ->
                TaskRow(
                    task = task,
                    onToggleDone = {
                        tasks = toggleDone(tasks, task.id)
                        visibleTasks = tasks
                    }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun TaskRow(
    task: Task,
    onToggleDone: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "due: ${task.dueDate}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(onClick = onToggleDone) {
            Text(if (task.done) "Undo" else "Done")
        }
    }
}
