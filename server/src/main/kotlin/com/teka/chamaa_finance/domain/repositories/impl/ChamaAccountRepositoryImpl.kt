package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.domain.repositories.ChamaAccountRepository
import com.teka.chamaa_finance.dtos.ChamaAccountDTO

class ChamaAccountRepositoryImpl: ChamaAccountRepository {
    override suspend fun allChamaaAccount(): List<ChamaAccountDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun chamaaAccountById(name: String): ChamaAccountDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun addChamaaAccount(chamaaAccount: ChamaAccountDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun removeChamaaAccount(id: String): Boolean {
        TODO("Not yet implemented")
    }
}