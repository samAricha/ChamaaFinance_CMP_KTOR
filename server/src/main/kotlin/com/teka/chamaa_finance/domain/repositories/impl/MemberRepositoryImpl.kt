package com.teka.chamaa_finance.domain.repositories.impl


import com.teka.chamaa_finance.db.tables.MemberDAO
import com.teka.chamaa_finance.db.tables.MemberTable
import com.teka.chamaa_finance.db.tables.daoToMemberDTO
import com.teka.chamaa_finance.db.tables.suspendTransaction
import com.teka.chamaa_finance.domain.repositories.MemberRepository
import com.teka.chamaa_finance.dtos.MemberDTO
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class MemberRepositoryImpl : MemberRepository {
    override suspend fun allMembers(): List<MemberDTO> = suspendTransaction {
        MemberDAO.all().map(::daoToMemberDTO)
    }

    override suspend fun memberById(id: String): MemberDTO? = suspendTransaction {
        MemberDAO
            .find { MemberTable.memberId eq id }
            .map(::daoToMemberDTO)
            .firstOrNull()
    }

    override suspend fun addMember(member: MemberDTO): Unit = suspendTransaction {
        MemberDAO.new {
            memberId = member.memberId
            firstName = member.firstName
            lastName = member.lastName
            phone = member.phone
        }
    }

    override suspend fun removeMember(id: String): Boolean = suspendTransaction {
        val rowsDeleted = MemberTable.deleteWhere {
            MemberTable.memberId eq id
        }
        rowsDeleted == 1
    }

}
