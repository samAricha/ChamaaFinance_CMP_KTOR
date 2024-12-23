package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.db.tables.ContributionDAO
import com.teka.chamaa_finance.db.tables.ContributionTable
import com.teka.chamaa_finance.db.tables.daoToContributionDTO
import com.teka.chamaa_finance.db.tables.suspendTransaction
import com.teka.chamaa_finance.domain.repositories.ContributionsRepository
import com.teka.chamaa_finance.dtos.ContributionDTO
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class ContributionsRepositoryImpl : ContributionsRepository {
    override suspend fun allContributions(): List<ContributionDTO> = suspendTransaction {
        ContributionDAO.all().map(::daoToContributionDTO)
    }

    override suspend fun contributionById(id: String): ContributionDTO? = suspendTransaction {
        ContributionDAO
            .find { ContributionTable.contributionId eq id }
            .map(::daoToContributionDTO)
            .firstOrNull()
    }

    override suspend fun addContribution(contribution: ContributionDTO): Unit = suspendTransaction {
        ContributionDAO.new {
            contributionId = contribution.contributionId
            chamaaId = contribution.chamaaId
            memberId = contribution.memberId
            contributionAmount = contribution.contributionAmount
            contributionDate = contribution.contributionDate
        }
    }

    override suspend fun removeContribution(contributionId: String): Boolean = suspendTransaction {
        val rowsDeleted = ContributionTable.deleteWhere {
            ContributionTable.contributionId eq contributionId
        }
        rowsDeleted == 1
    }

}
