package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.db.tables.ChamaMembersDAO
import com.teka.chamaa_finance.db.tables.ChamaMembersTable
import com.teka.chamaa_finance.db.tables.daoToChamaMembersDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import com.teka.chamaa_finance.db.tables.suspendTransaction
import com.teka.chamaa_finance.domain.repositories.ChamaMembersRepository
import com.teka.chamaa_finance.dtos.ChamaMembersDTO
import org.jetbrains.exposed.sql.deleteWhere

class ChamaMembersRepositoryImpl : ChamaMembersRepository {
    override suspend fun allChamaaMembers(): List<ChamaMembersDTO> = suspendTransaction {
        ChamaMembersDAO.all().map(::daoToChamaMembersDTO)
    }

    override suspend fun chamaaMemberById(id: String): ChamaMembersDTO? = suspendTransaction {
        ChamaMembersDAO
            .find { ChamaMembersTable.memberId eq id }
            .map(::daoToChamaMembersDTO)
            .firstOrNull()
    }

    override suspend fun addChamaaMember(chamaaMember: ChamaMembersDTO): Unit = suspendTransaction {
        ChamaMembersDAO.new {
            chamaId = chamaaMember.chamaId
            memberId = chamaaMember.memberId
        }
    }

    override suspend fun removeChamaaMember(id: String): Boolean = suspendTransaction {
        val rowsDeleted = ChamaMembersTable.deleteWhere {
            ChamaMembersTable.chamaId eq id
        }
        rowsDeleted == 1
    }

}
