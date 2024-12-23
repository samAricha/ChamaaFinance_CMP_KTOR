package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.domain.repositories.ChamaaRepository
import com.teka.chamaa_finance.dtos.ChamaDTO

class ChamaRepositoryImpl : ChamaaRepository {
    override suspend fun allChamaas(): List<ChamaDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun chamaaById(name: String): ChamaDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun addChamaa(chamaa: ChamaDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun removeChamaa(id: String): Boolean {
        TODO("Not yet implemented")
    }


}
