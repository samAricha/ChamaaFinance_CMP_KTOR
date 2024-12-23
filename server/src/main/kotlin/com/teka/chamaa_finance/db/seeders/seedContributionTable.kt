package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import com.teka.chamaa_finance.db.tables.ContributionTable

fun seedContributionTable() {
    transaction {
        ContributionTable.insert {
            it[contributionId] = "contribution_1"
            it[memberId] = "member_1"
            it[chamaAccountId] = "account_1"
            it[contributionDate] = "2024-01-01"
            it[contributionAmount] = "1000"
        }

        ContributionTable.insert {
            it[contributionId] = "contribution_2"
            it[memberId] = "member_2"
            it[chamaAccountId] = "account_2"
            it[contributionDate] = "2024-02-01"
            it[contributionAmount] = "2000"
        }
    }
}
