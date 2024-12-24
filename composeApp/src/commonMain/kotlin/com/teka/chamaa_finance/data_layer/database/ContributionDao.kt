package com.teka.chamaa_finance.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teka.chamaa_finance.data_layer.entities.ContributionEntity
import com.teka.chamaa_finance.data_layer.entities.MemberEntity

@Dao
interface ContributionDao {
    @Query("SELECT * FROM members")
    suspend fun getAllContributions(): List<ContributionEntity>

    @Update
    suspend fun updateContribution(contribution: ContributionEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun createContribution(contribution: ContributionEntity)

    @Query("DELETE FROM contributions WHERE id = :contributionId")
    suspend fun deleteContributionById(contributionId: Long)

    @Query("SELECT * FROM contributions WHERE id = :contributionId")
    suspend fun getContributionById(contributionId: Long): ContributionEntity?
}
