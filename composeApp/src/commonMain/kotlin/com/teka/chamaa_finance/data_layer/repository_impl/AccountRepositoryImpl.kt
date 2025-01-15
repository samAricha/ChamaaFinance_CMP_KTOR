package com.teka.chamaa_finance.data_layer.repository_impl

import com.teka.chamaa_finance.data_layer.datasource.impl.AccountLocalDataSourceImpl
import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity
import com.teka.chamaa_finance.domain.repositories.AccountRepository


class AccountRepositoryImpl(
    private val accountLocalDataSource: AccountLocalDataSourceImpl
) : AccountRepository {

    override suspend fun getAllAccounts(): List<ChamaAccountEntity> {
        return accountLocalDataSource.getAllAccounts()
    }

    override suspend fun createAccount(account: ChamaAccountEntity) {
        return accountLocalDataSource.createAccount(account)
    }

    override suspend fun updateAccount(account: ChamaAccountEntity) {
        return accountLocalDataSource.updateAccount(account)
    }

    override suspend fun deleteAccount(id: Long) {
        return accountLocalDataSource.deleteAccount(id)
    }

    override suspend fun getAccount(id: Long): ChamaAccountEntity? {
        return accountLocalDataSource.getAccount(id)
    }

}