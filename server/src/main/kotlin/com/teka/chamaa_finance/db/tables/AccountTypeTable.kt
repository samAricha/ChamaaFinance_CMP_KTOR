package com.teka.chamaa_finance.db.tables;

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

// Define the AccountType table
object AccountTypeTable : IntIdTable("account_type") {
    val accountTypeId = varchar("account_type_id", 50)
    val accountName = varchar("account_name", 100)
    val createdAt = varchar("created_at", 50)
    val updatedAt = varchar("updated_at", 50)
}

// Define the AccountType DAO (Data Access Object)
class AccountTypeDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AccountTypeDAO>(AccountTypeTable)

    var accountTypeId by AccountTypeTable.accountTypeId
    var accountName by AccountTypeTable.accountName
    var createdAt by AccountTypeTable.createdAt
    var updatedAt by AccountTypeTable.updatedAt
}

// A simple function to create a new account type entry
fun createAccountType(accountTypeId: String, accountName: String, createdAt: String, updatedAt: String) {
    transaction {
        AccountTypeTable.insert {
            it[AccountTypeTable.accountTypeId] = accountTypeId
            it[AccountTypeTable.accountName] = accountName
            it[AccountTypeTable.createdAt] = createdAt
            it[AccountTypeTable.updatedAt] = updatedAt
        }
    }
}

// A function to retrieve all account types
fun getAllAccountTypes(): List<AccountTypeDAO> {
    return transaction {
        AccountTypeDAO.all().toList()
    }
}

// A function to retrieve an account type by ID
fun getAccountTypeById(accountTypeId: String): AccountTypeDAO? {
    return transaction {
        AccountTypeDAO.find { AccountTypeTable.accountTypeId eq accountTypeId }.singleOrNull()
    }
}


