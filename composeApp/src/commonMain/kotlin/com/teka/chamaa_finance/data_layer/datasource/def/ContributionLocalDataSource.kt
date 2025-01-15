package com.teka.chamaa_finance.data_layer.datasource.def

import com.teka.chamaa_finance.data_layer.entities.ContributionEntity
import com.teka.chamaa_finance.data_layer.entities.MemberEntity

interface ContributionLocalDataSource {
    suspend fun getAllContributions(): List<ContributionEntity>
    suspend fun createContribution(contribution: ContributionEntity)
    suspend fun deleteContribution(id: Long)
    suspend fun updateContribution(contribution: ContributionEntity)
    suspend fun getContribution(id: Long): ContributionEntity?
}