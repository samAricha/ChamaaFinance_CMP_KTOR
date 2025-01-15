package com.teka.chamaa_finance.db.tables;

import com.teka.chamaa_finance.dtos.AccountTypeDTO
import com.teka.chamaa_finance.dtos.MemberDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

// Define the AccountType table
object AccountTypeTable : IntIdTable("account_type") {
    val accountTypeId = integer("account_type_id").autoIncrement().uniqueIndex()
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

// DAO to DTO Converter
fun daoToAccountTypeDTO(dao: AccountTypeDAO) = AccountTypeDTO(
    accountTypeId = dao.accountTypeId,
    accountName = dao.accountName,
    created_at = dao.createdAt,
    updated_at = dao.updatedAt
)

// DTO to DAO Converter
suspend fun AccountTypeDTO.toDAO(): AccountTypeDAO = suspendTransaction {
    AccountTypeDAO.new {
        accountTypeId = this@toDAO.accountTypeId
        accountName = this@toDAO.accountName
        createdAt = this@toDAO.created_at.toString()
        updatedAt = this@toDAO.updated_at.toString()
    }
}



