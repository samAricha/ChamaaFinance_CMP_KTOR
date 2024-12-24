package com.teka.chamaa_finance.data_layer.datasource.impl

import com.teka.chamaa_finance.data_layer.database.PeopleDatabase
import com.teka.chamaa_finance.data_layer.datasource.def.AccountLocalDataSource
import com.teka.chamaa_finance.data_layer.datasource.def.GroupLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity


class AccountLocalDataSourceImpl(
    private val appDatabase: PeopleDatabase
) : AccountLocalDataSource {

    override suspend fun getAllAccounts(): List<ChamaAccountEntity> {
        return appDatabase.accountDao().getAllAccounts()
    }

    override suspend fun createAccount(account: ChamaAccountEntity) {
        return appDatabase.accountDao().createAccount(account)
    }

    override suspend fun deleteAccount(id: Long) {
        return appDatabase.accountDao().deleteAccountById(id)
    }

    override suspend fun updateAccount(account: ChamaAccountEntity) {
        return appDatabase.accountDao().updateAccount(account)
    }

    override suspend fun getAccount(id: Long): ChamaAccountEntity? {
        return appDatabase.accountDao().getAccountById(id)
    }

}