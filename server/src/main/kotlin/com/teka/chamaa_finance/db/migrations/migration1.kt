package com.teka.chamaa_finance.db.migrations

import com.teka.chamaa_finance.db.tables.AccountTypeTable
import com.teka.chamaa_finance.db.tables.ChamaAccountTable
import com.teka.chamaa_finance.db.tables.ChamaMembersTable
import com.teka.chamaa_finance.db.tables.ChamaTable
import com.teka.chamaa_finance.db.tables.ContributionTable
import com.teka.chamaa_finance.db.tables.MemberTable
import com.teka.chamaa_finance.db.tables.TaskTable
import com.teka.chamaa_finance.dtos.ChamaDTO
import org.jetbrains.exposed.sql.SchemaUtils

fun migration1(){
        SchemaUtils.create(TaskTable)
        SchemaUtils.create(ChamaTable)
        SchemaUtils.create(AccountTypeTable)
        SchemaUtils.create(MemberTable)
        SchemaUtils.create(ChamaAccountTable)
        SchemaUtils.create(ChamaMembersTable)
        SchemaUtils.create(ContributionTable)
}