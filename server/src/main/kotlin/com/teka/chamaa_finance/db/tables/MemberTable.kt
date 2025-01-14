package com.teka.chamaa_finance.db.tables;

import com.teka.chamaa_finance.dtos.MemberDTO
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.EntityID


// Table Definition
object MemberTable : IntIdTable("member") {
    val memberId = varchar("member_id", 50).uniqueIndex()
    val firstName = varchar("first_name", 100)
    val lastName = varchar("last_name", 100)
    val phone = varchar("phone", 15)
    val dateJoined = varchar("date_joined", 50)
}


// DAO Definition
class MemberDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MemberDAO>(MemberTable)

    var memberId by MemberTable.memberId
    var firstName by MemberTable.firstName
    var lastName by MemberTable.lastName
    var phone by MemberTable.phone
    var dateJoined by MemberTable.dateJoined
}



// DAO to DTO Converter
fun daoToMemberDTO(dao: MemberDAO) = MemberDTO(
    memberId = dao.memberId,
    firstName = dao.firstName,
    lastName = dao.lastName,
    phone = dao.phone,
    dateJoined = dao.dateJoined
)


fun MemberDAO.toDTO(): MemberDTO {
    return MemberDTO(
        memberId = this.memberId,
        firstName = this.firstName,
        lastName = this.lastName,
        phone = this.phone,
        dateJoined = this.dateJoined
    )
}


// DTO to DAO Converter
suspend fun MemberDTO.toDAO(): MemberDAO = suspendTransaction {
    MemberDAO.new {
        memberId = this@toDAO.memberId
        firstName = this@toDAO.firstName
        lastName = this@toDAO.lastName
        phone = this@toDAO.phone
        dateJoined = this@toDAO.dateJoined
    }
}

// Fetch All Members
suspend fun fetchAllMembers(): List<MemberDTO> = suspendTransaction {
    MemberDAO.all().map { daoToMemberDTO(it) }
}

// Add a New Member
suspend fun addMember(memberDTO: MemberDTO) {
    memberDTO.toDAO()
}
