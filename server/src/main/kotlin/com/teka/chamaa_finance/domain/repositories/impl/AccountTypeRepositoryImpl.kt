package com.teka.chamaa_finance.domain.repositories.impl;

import com.teka.chamaa_finance.db.tables.AccountTypeDAO
import com.teka.chamaa_finance.db.tables.AccountTypeTable
import com.teka.chamaa_finance.db.tables.daoToAccountTypeDTO
import com.teka.chamaa_finance.db.tables.suspendTransaction
import com.teka.chamaa_finance.domain.repositories.AccountTypeRepository
import com.teka.chamaa_finance.dtos.AccountTypeDTO
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class AccountTypeRepositoryImpl : AccountTypeRepository {
    override suspend fun allAccountTypes(): List<AccountTypeDTO> = suspendTransaction {
        AccountTypeDAO.all().map(::daoToAccountTypeDTO)
    }

    override suspend fun accountTypeById(accountTypeId: Int): AccountTypeDTO? = suspendTransaction {
        AccountTypeDAO
            .find { AccountTypeTable.accountTypeId eq accountTypeId }
            .map(::daoToAccountTypeDTO)
            .firstOrNull()
    }

    override suspend fun addAccountType(accountType: AccountTypeDTO): Unit = suspendTransaction {
        AccountTypeDAO.new {
            accountTypeId = accountType.accountTypeId
            accountName = accountType.accountName
            createdAt = accountType.created_at.toString()
            updatedAt = accountType.updated_at.toString()
        }
    }

    override suspend fun removeAccountType(accountTypeId: Int): Boolean = suspendTransaction {
        val rowsDeleted = AccountTypeTable.deleteWhere {
            AccountTypeTable.accountTypeId eq accountTypeId
        }
        rowsDeleted == 1
    }

}
