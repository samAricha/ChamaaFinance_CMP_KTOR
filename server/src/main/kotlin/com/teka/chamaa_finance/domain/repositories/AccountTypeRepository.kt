package com.teka.chamaa_finance.domain.repositories;

import com.teka.chamaa_finance.dtos.AccountTypeDTO

interface AccountTypeRepository {
    suspend fun allAccountTypes(): List<AccountTypeDTO>
    suspend fun accountTypeById(accountTypeId: Int): AccountTypeDTO?
    suspend fun addAccountType(accountType: AccountTypeDTO): Unit
    suspend fun removeAccountType(accountTypeId: Int): Boolean
}
