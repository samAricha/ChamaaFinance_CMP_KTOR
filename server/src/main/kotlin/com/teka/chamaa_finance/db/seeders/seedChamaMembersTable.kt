package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.teka.chamaa_finance.db.tables.ChamaMembersTable

fun seedChamaMembersTable() {
    transaction {
        ChamaMembersTable.insert {
            it[chamaId] = "chama_1"
            it[memberId] = "member_1"
        }

        ChamaMembersTable.insert {
            it[chamaId] = "chama_2"
            it[memberId] = "member_2"
        }
    }
}
