package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.dtos.ChamaAccountDTO
import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task

interface ChamaAccountRepository {
    suspend fun allChamaaAccount(): List<ChamaAccountDTO>
    suspend fun chamaaAccountById(name: String): ChamaAccountDTO?
    suspend fun addChamaaAccount(chamaaAccount: ChamaAccountDTO): ChamaAccountDTO
    suspend fun removeChamaaAccount(name: String): Boolean
}