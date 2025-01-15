package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.teka.chamaa_finance.db.tables.MemberTable

// Seeder for Member table
fun seedMemberTable() {
    seedTableIfEmpty(MemberTable) {
        MemberTable.insert {
            it[memberId] = "member_1"
            it[firstName] = "John"
            it[lastName] = "Doe"
            it[phone] = "123456789"
            it[dateJoined] = "2024-01-01"
        }

        MemberTable.insert {
            it[memberId] = "member_2"
            it[firstName] = "Jane"
            it[lastName] = "Doe"
            it[phone] = "987654321"
            it[dateJoined] = "2024-02-01"
        }
    }
}

