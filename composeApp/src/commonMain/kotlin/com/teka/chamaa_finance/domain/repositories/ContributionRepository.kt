package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.data_layer.entities.ContributionEntity


interface ContributionRepository {
    suspend fun getAllContributions(): List<ContributionEntity>
    suspend fun createContribution(contribution: ContributionEntity)
    suspend fun updateContribution(contribution: ContributionEntity)
    suspend fun deleteContribution(id: Long)
    suspend fun getContribution(id: Long): ContributionEntity?
}