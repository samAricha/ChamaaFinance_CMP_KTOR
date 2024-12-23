package com.teka.chamaa_finance.plugins


import com.teka.chamaa_finance.db.migrations.migration1
import com.teka.chamaa_finance.db.seeders.TaskSeeder
import com.teka.chamaa_finance.db.seeders.*
import com.teka.chamaa_finance.model.TaskRepositoryImpl
import com.teka.chamaa_finance.model.TaskRepository
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction


fun Application.configureDataBase() {
    val database: Database = Database.connect(
        "jdbc:postgresql://127.0.0.1:5432/chamaa",
//        url ="jdbc:mysql://localhost:3306/organiks",
        user = "postgres",
        password = "toor"
    )
    val taskRepository: TaskRepository = TaskRepositoryImpl()

    transaction(database) {
        //migrations
        migration1()
        //seeders
        TaskSeeder.seed(taskRepository)
        seedChamaTable()
        seedChamaMembersTable()
        seedChamaAccountTable()
        seedAccountTypeTable()
        seedMemberTable()
        seedContributionTable()
    }

}