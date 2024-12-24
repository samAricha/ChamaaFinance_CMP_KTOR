package com.teka.chamaa_finance.data_layer.datasource.impl

import com.teka.chamaa_finance.data_layer.database.PeopleDatabase
import com.teka.chamaa_finance.data_layer.datasource.def.ContributionLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.def.MemberLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.ContributionEntity
import com.teka.chamaa_finance.data_layer.entities.MemberEntity


class ContributionLocalDataSourceImpl(private val appDatabase: PeopleDatabase) : ContributionLocalDataSource {

    override suspend fun getAllContributions(): List<ContributionEntity> {
        return appDatabase.contributionDao().getAllContributions()
    }

    override suspend fun createContribution(contribution: ContributionEntity) {
        return appDatabase.contributionDao().createContribution(contribution)
    }

    override suspend fun deleteContribution(id: Long) {
        return appDatabase.contributionDao().deleteContributionById(id)
    }

    override suspend fun updateContribution(contribution: ContributionEntity) {
        return appDatabase.contributionDao().updateContribution(contribution)
    }

    override suspend fun getContribution(id: Long): ContributionEntity? {
        return appDatabase.contributionDao().getContributionById(id)
    }

}