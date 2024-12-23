package com.teka.chamaa_finance.model;


import com.teka.chamaa_finance.db.tables.TaskDAO
import com.teka.chamaa_finance.db.tables.TaskTable
import com.teka.chamaa_finance.db.tables.daoToModel
import com.teka.chamaa_finance.db.tables.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class TaskRepositoryImpl : TaskRepository {
    override suspend fun allTasks(): List<Task> = suspendTransaction {
        TaskDAO.all().map(::daoToModel)
    }

    override suspend fun tasksByPriority(priority: Priority): List<Task> = suspendTransaction {
        TaskDAO
            .find { (TaskTable.priority eq priority.toString()) }
            .map(::daoToModel)
    }

    override suspend fun taskByName(name: String): Task? = suspendTransaction {
        TaskDAO
            .find { (TaskTable.name eq name) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun addTask(task: Task): Unit = suspendTransaction {
        TaskDAO.new {
            name = task.name
            description = task.description
            priority = task.priority.toString()
        }
    }

    override suspend fun removeTask(name: String): Boolean = suspendTransaction {
        val rowsDeleted = TaskTable.deleteWhere {
            TaskTable.name eq name
        }
        rowsDeleted == 1
    }
}