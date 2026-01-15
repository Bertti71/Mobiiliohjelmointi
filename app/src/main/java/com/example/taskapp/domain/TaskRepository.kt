package com.example.taskapp.domain

import java.time.LocalDate

object TaskRepository {
    val mockTasks: List<Task> = listOf(
        Task(1, "osta maitoa", "laktoositon", 2, LocalDate.now().plusDays(1), false),
        Task(2, "", "", 3, LocalDate.now().plusDays(2), false),
        Task(3, "tee viikko 1 tehtävä", "kotlin", 3, LocalDate.now().plusDays(3), true),
        Task(4, "lenkki", "30min", 1, LocalDate.now().plusDays(1), true),
        Task(5, "siivoa", "imuroi", 2, LocalDate.now().plusDays(5), false)
    )
}
