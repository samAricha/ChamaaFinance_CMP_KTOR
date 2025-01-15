package com.teka.chamaa_finance.plugins


import com.teka.chamaa_finance.db.migrations.migration1
import com.teka.chamaa_finance.db.seeders.TaskSeeder
import com.teka.chamaa_finance.db.seeders.*
import com.teka.chamaa_finance.db.tables.MigrationTable
import com.teka.chamaa_finance.domain.repositories.impl.TaskRepositoryImpl
import com.teka.chamaa_finance.domain.repositories.TaskRepository
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


//fun Application.configureDataBase() {
//    val database: Database = Database.connect(
//        "jdbc:postgresql://127.0.0.1:5432/chamaa",
//        user = "postgres",
//        password = "toor"
//    )
//    val taskRepository: TaskRepository = TaskRepositoryImpl()
//
//    transaction(database) {
//        //migrations
//        migration1()
//        //seeders
//        TaskSeeder.seed(taskRepository)
//        seedChamaTable()
//        seedChamaMembersTable()
//        seedChamaAccountTable()
//        seedAccountTypeTable()
//        seedMemberTable()
//        seedContributionTable()
//    }
//
//}



fun Application.configureDataBase() {
    val database: Database = Database.connect(
        "jdbc:postgresql://127.0.0.1:5432/chamaa",
        user = "postgres",
        password = "toor"
    )

    // Run migrations and seeders
    transaction(database) {
        // Ensure migrations table exists
        SchemaUtils.create(MigrationTable)

        // Check if the migration has already been run
        if (!MigrationTable.existsMigration("migration1")) {
            // Run the migration
            migration1()
            // Mark migration as applied
            MigrationTable.insertMigration("migration1")
        }

        // Seed data
        seedData()
    }

}


fun seedData() {
    val taskRepository: TaskRepository = TaskRepositoryImpl()

    // Insert data into your tables
    TaskSeeder.seed(taskRepository)
    seedChamaTable()
    seedChamaMembersTable()
    seedChamaAccountTable()
    seedAccountTypeTable()
    seedMemberTable()
    seedContributionTable()
}


//object MigrationTable : Table("migrations") {
//    val name = varchar("name", 255)
//
//    override val primaryKey = PrimaryKey(name, name = "PK_MigrationTable_Name")
//
//    fun existsMigration(migrationName: String): Boolean = transaction {
//        select { name eq migrationName }.count() > 0
//    }
//
//    fun insertMigration(migrationName: String) = transaction {
//        insert {
//            it[name] = migrationName
//        }
//    }
//}
