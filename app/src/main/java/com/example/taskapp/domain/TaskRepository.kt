package com.example.taskapp.domain

import java.time.LocalDate

object TaskRepository {
    val mockTasks: List<Task> = listOf(
        Task(id = 1, title = "osta maitoa", description = "laktoositon", dueDate = LocalDate.now().plusDays(1), createdAt = LocalDate.now(), done = false
        ),
        Task(
            id = 2, title = "tee kotitehtävä", description = "matematiikka", dueDate = LocalDate.now().plusDays(2), createdAt = LocalDate.now(), done = false
        ),
        Task(
            id = 3, title = "tee viikko 1 tehtävä", description = "kotlin", dueDate = LocalDate.now().plusDays(3), createdAt = LocalDate.now(), done = true
        ),
        Task(
            id = 4, title = "lenkki", description = "30min", dueDate = LocalDate.now().plusDays(1), createdAt = LocalDate.now(), done = true
        ),
        Task(
            id = 5, title = "siivoa", description = "imuroi", dueDate = LocalDate.now().plusDays(5), createdAt = LocalDate.now(), done = false
        )
    )
}
