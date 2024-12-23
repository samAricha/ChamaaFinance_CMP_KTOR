package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.domain.repositories.ContributionsRepository
import com.teka.chamaa_finance.dtos.ContributionDTO

class ContributionsRepositoryImpl: ContributionsRepository {
    override suspend fun allContributions(): List<ContributionDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun contributionById(name: String): ContributionDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun addContribution(contribution: ContributionDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun removeContribution(id: String): Boolean {
        TODO("Not yet implemented")
    }
}