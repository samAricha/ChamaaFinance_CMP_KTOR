package com.teka.chamaa_finance.db.seeders;

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun isTableEmpty(table: Table): Boolean {
    return transaction {
        table.selectAll().count().toInt() == 0
    }
}
