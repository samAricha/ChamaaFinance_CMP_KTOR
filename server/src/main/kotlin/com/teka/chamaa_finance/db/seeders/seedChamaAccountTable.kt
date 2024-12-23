package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.teka.chamaa_finance.db.tables.ChamaAccountTable

fun seedChamaAccountTable() {
    transaction {
        ChamaAccountTable.insert {
            it[chamaAccountId] = "account_1"
            it[chamaId] = "chama_1"
            it[accountName] = "Chama One Account"
            it[accountTypeId] = "account_type_1"
            it[dateFormed] = "2024-01-01"
        }

        ChamaAccountTable.insert {
            it[chamaAccountId] = "account_2"
            it[chamaId] = "chama_2"
            it[accountName] = "Chama Two Account"
            it[accountTypeId] = "account_type_2"
            it[dateFormed] = "2024-02-01"
        }
    }
}
