package com.teka.chamaa_finance.db.tables;

import com.teka.chamaa_finance.dtos.ChamaMembersDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ChamaMembersTable : IntIdTable("chama_members") {
    val chamaId = varchar("chama_id", 50).references(ChamaTable.chamaId)
    val memberId = varchar("member_id", 50)
}

class ChamaMembersDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChamaMembersDAO>(ChamaMembersTable)

    var chamaId by ChamaMembersTable.chamaId
    var memberId by ChamaMembersTable.memberId
}


fun daoToChamaMembersDTO(dao: ChamaMembersDAO) = ChamaMembersDTO(
    chamaId = dao.chamaId,
    memberId = dao.memberId
)

fun ChamaMembersDAO.toDTO(): ChamaMembersDTO {
    return ChamaMembersDTO(
        chamaId = this.chamaId,
        memberId = this.memberId
    )
}


suspend fun ChamaMembersDTO.toDAO(): ChamaMembersDAO = suspendTransaction {
    ChamaMembersDAO.new {
        chamaId = this@toDAO.chamaId
        memberId = this@toDAO.memberId
    }
}

suspend fun fetchMembersForChama(chamaId: String): List<ChamaMembersDTO> = suspendTransaction {
    ChamaMembersDAO.find { ChamaMembersTable.chamaId eq chamaId }.map { daoToChamaMembersDTO(it) }
}

suspend fun addMemberToChama(memberDTO: ChamaMembersDTO) {
    memberDTO.toDAO()
}
