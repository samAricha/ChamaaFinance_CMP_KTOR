package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.domain.repositories.ChamaMembersRepository
import com.teka.chamaa_finance.dtos.ChamaMembersDTO

class ChamaMembersRepositoryImpl: ChamaMembersRepository {
    override suspend fun allChamaaMembers(): List<ChamaMembersDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun chamaaMemberById(name: String): ChamaMembersDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun addChamaaMember(chamaaMember: ChamaMembersDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun removeChamaaMember(id: String): Boolean {
        TODO("Not yet implemented")
    }
}