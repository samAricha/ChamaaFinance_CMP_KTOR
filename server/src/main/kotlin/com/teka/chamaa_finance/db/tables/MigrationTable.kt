package com.teka.chamaa_finance.db.tables;

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


object MigrationTable : Table("migrations") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    override val primaryKey = PrimaryKey(id)

    // Check if the migration has already been applied
    fun existsMigration(migrationName: String): Boolean {
        return transaction {
            // Properly reference the column in the `select` statement
            select { name.eq(migrationName) }.count() > 0
        }
    }
    fun insertMigration(migrationName: String) {
        transaction {
            insert {
                it[name] = migrationName
            }
        }
    }
}