package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.db.tables.ChamaDAO
import com.teka.chamaa_finance.db.tables.ChamaTable
import com.teka.chamaa_finance.db.tables.suspendTransaction
import com.teka.chamaa_finance.db.tables.toDTO
import com.teka.chamaa_finance.domain.repositories.ChamaaRepository
import com.teka.chamaa_finance.dtos.ChamaDTO
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class ChamaRepositoryImpl : ChamaaRepository {
    override suspend fun allChamaas(): List<ChamaDTO> = suspendTransaction {
        ChamaDAO.all().map(::daoToDTO)
    }

    override suspend fun chamaaById(id: String): ChamaDTO? = suspendTransaction {
        ChamaDAO
            .find { ChamaTable.chamaId eq id }
            .map(::daoToDTO)
            .firstOrNull()
    }

    override suspend fun addChamaa(chamaa: ChamaDTO): ChamaDTO = suspendTransaction {
        val savedChamaa: ChamaDAO = ChamaDAO.new {
            chamaId = chamaa.chamaId
            chamaName = chamaa.chamaName
            chamaDescription = chamaa.chamaDescription
            dateFormed = chamaa.dateFormed
        }

        savedChamaa.toDTO()
    }

    override suspend fun removeChamaa(id: String): Boolean = suspendTransaction {
        val rowsDeleted = ChamaTable.deleteWhere {
            ChamaTable.chamaId eq id
        }
        rowsDeleted == 1
    }

    // Helper to map DAO to DTO
    private fun daoToDTO(dao: ChamaDAO): ChamaDTO {
        return ChamaDTO(
            chamaId = dao.chamaId,
            chamaName = dao.chamaName,
            chamaDescription = dao.chamaDescription,
            dateFormed = dao.dateFormed
        )
    }
}

