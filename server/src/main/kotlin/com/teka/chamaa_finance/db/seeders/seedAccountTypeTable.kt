package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.teka.chamaa_finance.db.tables.AccountTypeTable

fun seedAccountTypeTable() {
    transaction {
        AccountTypeTable.insert {
            it[accountTypeId] = 1
            it[accountName] = "Savings Account"
            it[createdAt] = "2024-01-01"
            it[updatedAt] = "2024-01-01"
        }

        AccountTypeTable.insert {
            it[accountTypeId] = 2
            it[accountName] = "Checking Account"
            it[createdAt] = "2024-02-01"
            it[updatedAt] = "2024-02-01"
        }
    }
}
