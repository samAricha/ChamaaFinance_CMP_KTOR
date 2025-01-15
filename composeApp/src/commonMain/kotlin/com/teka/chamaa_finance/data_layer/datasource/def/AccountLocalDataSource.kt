package com.teka.chamaa_finance.data_layer.datasource.def

import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity

interface AccountLocalDataSource {
    suspend fun getAllAccounts(): List<ChamaAccountEntity>
    suspend fun createAccount(account: ChamaAccountEntity)
    suspend fun deleteAccount(id: Long)
    suspend fun updateAccount(account: ChamaAccountEntity)
    suspend fun getAccount(id: Long): ChamaAccountEntity?
}