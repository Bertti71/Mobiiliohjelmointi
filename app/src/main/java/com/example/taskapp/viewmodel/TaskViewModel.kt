package com.example.taskapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskapp.domain.Task
import com.example.taskapp.domain.TaskRepository
import java.time.LocalDate

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set

    private var allTasks: List<Task> = emptyList()
    private var nextId = 1

    init {
        allTasks = TaskRepository.mockTasks
        tasks = allTasks
        nextId = (allTasks.maxOfOrNull { it.id } ?: 0) + 1
    }

    fun addTaskFromInputs(title: String, description: String, dueDateStr: String): Boolean {
        val t = title.trim()
        if (t.isEmpty()) return false

        val due = try { LocalDate.parse(dueDateStr)
        } catch (e: Exception) {
            return false
        }

        val newTask = Task(id = nextId++, title = t, description = description, dueDate = due, createdAt = LocalDate.now(), done = false
        )

        allTasks = allTasks + newTask
        tasks = allTasks
        return true
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { task ->
            if (task.id == id) task.copy(done = !task.done)
            else task
        }
        tasks = allTasks
    }

    fun removeTask(id: Int) { allTasks = allTasks.filterNot { it.id == id }
        tasks = allTasks
    }

    fun filterByDone(done: Boolean) { tasks = allTasks.filter { it.done == done }
    }

    fun showAll() { tasks = allTasks
    }

    fun sortByDueDate() { tasks = tasks.sortedBy { it.dueDate }
    }
}
