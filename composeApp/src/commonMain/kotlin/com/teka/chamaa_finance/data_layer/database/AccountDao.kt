package com.teka.chamaa_finance.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM chama_accounts")
    suspend fun getAllAccounts(): List<ChamaAccountEntity>

    @Update
    suspend fun updateAccount(account: ChamaAccountEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun createAccount(account: ChamaAccountEntity)

    @Query("DELETE FROM chama_accounts WHERE id = :accountId")
    suspend fun deleteAccountById(accountId: Long)

    @Query("SELECT * FROM chama_accounts WHERE id = :accountId")
    suspend fun getAccountById(accountId: Long): ChamaAccountEntity?
}
