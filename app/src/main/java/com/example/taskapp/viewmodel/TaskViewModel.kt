package com.example.taskapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskapp.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private var nextId = 1

    init {
        _tasks.value = listOf(
            Task(nextId++, "Osta maitoa", "Laktoositon", LocalDate.now().plusDays(1)),
            Task(nextId++, "Tee viikko 3", "MVVM", LocalDate.now().plusDays(2)),
            Task(nextId++, "Lenkki", "30 min", LocalDate.now().plusDays(3), done = true)
        )
    }

    fun addTask(title: String, description: String, dueDate: LocalDate) {
        val t = title.trim()
        if (t.isEmpty()) return

        val newTask = Task(
            id = nextId++,
            title = t,
            description = description.trim(),
            dueDate = dueDate,
            done = false
        )
        _tasks.value = _tasks.value + newTask
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(done = !task.done) else task
        }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == id }
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == updated.id) updated else task
        }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }
}
