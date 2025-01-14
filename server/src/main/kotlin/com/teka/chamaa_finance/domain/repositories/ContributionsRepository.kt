package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.dtos.ContributionDTO
import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task

interface ContributionsRepository {
    suspend fun allContributions(): List<ContributionDTO>
    suspend fun contributionById(name: String): ContributionDTO?
    suspend fun addContribution(contribution: ContributionDTO): ContributionDTO
    suspend fun removeContribution(name: String): Boolean
}