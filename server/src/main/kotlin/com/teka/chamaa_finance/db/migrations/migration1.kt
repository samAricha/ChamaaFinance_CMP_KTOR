package com.teka.chamaa_finance.db.migrations

import com.teka.chamaa_finance.db.TaskTable
import org.jetbrains.exposed.sql.SchemaUtils

fun migration1(){
        SchemaUtils.create(TaskTable)
}