package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import com.teka.chamaa_finance.db.tables.ChamaTable


// Seeder for Chama table
fun seedChamaTable() {
    seedTableIfEmpty(ChamaTable) {
        // Inserting Chama records if the table is empty
        ChamaTable.insert {
            it[chamaId] = "chama_1"
            it[chamaName] = "Chama One"
            it[chamaDescription] = "Description of Chama One"
            it[dateFormed] = "2024-01-01"
        }

        ChamaTable.insert {
            it[chamaId] = "chama_2"
            it[chamaName] = "Chama Two"
            it[chamaDescription] = "Description of Chama Two"
            it[dateFormed] = "2024-02-01"
        }
    }
}

