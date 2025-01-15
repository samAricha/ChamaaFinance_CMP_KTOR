package com.teka.chamaa_finance.db.tables;

import com.teka.chamaa_finance.dtos.ChamaAccountDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.EntityID

// Table Definition
object ChamaAccountTable : IntIdTable("chama_account") {
    val chamaAccountId = varchar("chama_account_id", 50)
    val chamaId = varchar("chama_id", 50).references(ChamaTable.chamaId)
    val accountName = varchar("account_name", 100)
    val accountTypeId = varchar("account_type_id", 50)
    val dateFormed = varchar("date_formed", 50)
}

// DAO Definition
class ChamaAccountDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChamaAccountDAO>(ChamaAccountTable)

    var chamaAccountId by ChamaAccountTable.chamaAccountId
    var chamaId by ChamaAccountTable.chamaId
    var accountName by ChamaAccountTable.accountName
    var accountTypeId by ChamaAccountTable.accountTypeId
    var dateFormed by ChamaAccountTable.dateFormed
}


fun ChamaAccountDAO.toDTO(): ChamaAccountDTO {
    return ChamaAccountDTO(
        chamaAccountId = this.chamaAccountId,
        chamaId = this.chamaId,
        accountName = this.accountName,
        accountTypeId = this.accountTypeId,
        dateFormed = this.dateFormed
    )
}


// DTO to DAO Converter
suspend fun ChamaAccountDTO.toDAO(): ChamaAccountDAO = suspendTransaction {
    ChamaAccountDAO.new {
        chamaAccountId = this@toDAO.chamaAccountId
        chamaId = this@toDAO.chamaId
        accountName = this@toDAO.accountName
        accountTypeId = this@toDAO.accountTypeId
        dateFormed = this@toDAO.dateFormed
    }
}

// Fetch All Chama Accounts
suspend fun fetchAllChamaAccounts(): List<ChamaAccountDTO> = suspendTransaction {
    ChamaAccountDAO.all().map { it.toDTO() }
}

// Add a New Chama Account
suspend fun addChamaAccount(accountDTO: ChamaAccountDTO) {
    accountDTO.toDAO()
}
