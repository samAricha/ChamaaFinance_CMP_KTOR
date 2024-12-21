package com.teka.chamaa_finance.plugins


import com.teka.chamaa_finance.db.migrations.migration1
import com.teka.chamaa_finance.db.seeders.TaskSeeder
import com.teka.chamaa_finance.model.TaskRepositoryImpl
import com.teka.chamaa_finance.model.TaskRepository
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.configureDataBase() {
    val database: Database = Database.connect(
        url ="jdbc:mysql://localhost:3306/organiks",
        user = "root",
        password = ""
    )
    val taskRepository: TaskRepository = TaskRepositoryImpl()

    transaction(database) {
        //migrations
        migration1()
        //seeders
        TaskSeeder.seed(taskRepository)
    }

}