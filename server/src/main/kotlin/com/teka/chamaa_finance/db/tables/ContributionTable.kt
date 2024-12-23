package com.teka.chamaa_finance.db.tables;

import com.teka.chamaa_finance.dtos.ContributionDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.EntityID


// Table Definition
object ContributionTable : IntIdTable("contribution") {
    val contributionId = varchar("contribution_id", 50)
    val memberId = varchar("member_id", 50)
    val chamaAccountId = varchar("chama_account_id", 50)
    val contributionDate = varchar("contribution_date", 50)
    val contributionAmount = varchar("contribution_amount", 50)
}

// DAO Definition
class ContributionDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ContributionDAO>(ContributionTable)

    var contributionId by ContributionTable.contributionId
    var memberId by ContributionTable.memberId
    var chamaAccountId by ContributionTable.chamaAccountId
    var contributionDate by ContributionTable.contributionDate
    var contributionAmount by ContributionTable.contributionAmount
}


// DAO to DTO Converter
fun daoToContributionDTO(dao: ContributionDAO) = ContributionDTO(
    contributionId = dao.contributionId,
    memberId = dao.memberId,
    chamaAccountId = dao.chamaAccountId,
    contributionDate = dao.contributionDate,
    contributionAmount = dao.contributionAmount
)

// DTO to DAO Converter
suspend fun ContributionDTO.toDAO(): ContributionDAO = suspendTransaction {
    ContributionDAO.new {
        contributionId = this@toDAO.contributionId
        memberId = this@toDAO.memberId
        chamaAccountId = this@toDAO.chamaAccountId
        contributionDate = this@toDAO.contributionDate
        contributionAmount = this@toDAO.contributionAmount
    }
}

// Fetch All Contributions
suspend fun fetchAllContributions(): List<ContributionDTO> = suspendTransaction {
    ContributionDAO.all().map { daoToContributionDTO(it) }
}

// Add a New Contribution
suspend fun addContribution(contributionDTO: ContributionDTO) {
    contributionDTO.toDAO()
}
