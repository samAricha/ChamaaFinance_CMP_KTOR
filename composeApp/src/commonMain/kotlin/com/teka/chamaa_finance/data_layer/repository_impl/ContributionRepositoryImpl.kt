package com.teka.chamaa_finance.data_layer.repository_impl

import com.teka.chamaa_finance.data_layer.datasource.def.ContributionLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.ContributionEntity
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.domain.repositories.ContributionRepository


class ContributionRepositoryImpl(
    private val contributionLocalDataSource: ContributionLocalDataSource
) : ContributionRepository {

    override suspend fun getAllContributions(): List<ContributionEntity> {
        return contributionLocalDataSource.getAllContributions()
    }

    override suspend fun createContribution(contribution: ContributionEntity) {
        return contributionLocalDataSource.createContribution(contribution)
    }

    override suspend fun updateContribution(contribution: ContributionEntity) {
        return contributionLocalDataSource.updateContribution(contribution)
    }

    override suspend fun deleteContribution(id: Long) {
        return contributionLocalDataSource.deleteContribution(id)
    }

    override suspend fun getContribution(id: Long): ContributionEntity? {
        return contributionLocalDataSource.getContribution(id)
    }

}