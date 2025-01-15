package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task

interface TaskRepository {
    suspend fun allTasks(): List<Task>
    suspend fun tasksByPriority(priority: Priority): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(name: String): Boolean
}