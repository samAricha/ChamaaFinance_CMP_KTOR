package com.teka.chamaa_finance.db.seeders;

import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task
import com.teka.chamaa_finance.domain.repositories.TaskRepository
import kotlinx.coroutines.runBlocking

object TaskSeeder {
    fun seed(taskRepository: TaskRepository) {
        runBlocking {
            // Check if there are existing tasks before seeding
            if (taskRepository.allTasks().isEmpty()) {
                // Add sample tasks
                val sampleTasks = listOf(
                    Task(name = "Task 1", description = "Description for Task 1", priority = Priority.High),
                    Task(name = "Task 2", description = "Description for Task 2", priority = Priority.Medium),
                    Task(name = "Task 3", description = "Description for Task 3", priority = Priority.Low),
                )

                sampleTasks.forEach { task ->
                    taskRepository.addTask(task)
                }
                println("Task repository seeded successfully.")
            } else {
                println("Task repository already contains data. Skipping seeding.")
            }
        }
    }
}

