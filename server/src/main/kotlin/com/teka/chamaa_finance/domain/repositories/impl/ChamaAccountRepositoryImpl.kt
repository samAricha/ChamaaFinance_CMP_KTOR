package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.db.tables.ChamaAccountDAO
import com.teka.chamaa_finance.db.tables.ChamaAccountTable
import com.teka.chamaa_finance.db.tables.suspendTransaction
import com.teka.chamaa_finance.domain.repositories.ChamaAccountRepository
import com.teka.chamaa_finance.dtos.ChamaAccountDTO
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class ChamaAccountRepositoryImpl : ChamaAccountRepository {
    override suspend fun allChamaaAccount(): List<ChamaAccountDTO> = suspendTransaction {
        ChamaAccountDAO.all().map(::daoToDTO)
    }

    override suspend fun chamaaAccountById(id: String): ChamaAccountDTO? = suspendTransaction {
        ChamaAccountDAO
            .find { ChamaAccountTable.chamaAccountId eq id }
            .map(::daoToDTO)
            .firstOrNull()
    }

    override suspend fun addChamaaAccount(chamaaAccount: ChamaAccountDTO): Unit = suspendTransaction {
        ChamaAccountDAO.new {
            chamaAccountId = chamaaAccount.chamaAccountId
            chamaId = chamaaAccount.chamaId
            accountName = chamaaAccount.accountName
            accountTypeId = chamaaAccount.accountTypeId
            dateFormed = chamaaAccount.dateFormed
        }
    }

    override suspend fun removeChamaaAccount(id: String): Boolean = suspendTransaction {
        val rowsDeleted = ChamaAccountTable.deleteWhere {
            ChamaAccountTable.chamaAccountId eq id
        }
        rowsDeleted == 1
    }

    // Helper to map DAO to DTO
    private fun daoToDTO(dao: ChamaAccountDAO): ChamaAccountDTO {
        return ChamaAccountDTO(
            chamaAccountId = dao.chamaAccountId,
            chamaId = dao.chamaId,
            accountName = dao.accountName,
            accountTypeId = dao.accountTypeId,
            dateFormed = dao.dateFormed
        )
    }
}
