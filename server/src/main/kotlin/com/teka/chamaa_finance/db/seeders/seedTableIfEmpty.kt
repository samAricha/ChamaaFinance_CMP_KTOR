package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

// General function to insert data if the table is empty


fun <T : Table> seedTableIfEmpty(table: T, insertData: () -> Unit) {
    if (isTableEmpty(table)) {
        transaction {
            insertData()
            println("${table.tableName} table seeded.")
        }
    } else {
        println("${table.tableName} table already has data.")
    }
}