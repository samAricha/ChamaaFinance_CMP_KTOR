package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity


interface AccountRepository {
    suspend fun getAllAccounts(): List<ChamaAccountEntity>
    suspend fun createAccount(group: ChamaAccountEntity)
    suspend fun updateAccount(group: ChamaAccountEntity)
    suspend fun deleteAccount(id: Long)
    suspend fun getAccount(id: Long): ChamaAccountEntity?
}