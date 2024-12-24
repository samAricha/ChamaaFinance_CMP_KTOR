package com.teka.chamaa_finance.data_layer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity

@Dao
interface GroupDao {
    @Query("SELECT * FROM chamas")
    suspend fun getAllGroups(): List<ChamaEntity>

    @Update
    suspend fun updateGroup(group: ChamaEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun createGroup(group: ChamaEntity)

    @Query("DELETE FROM chamas WHERE id = :chamaId")
    suspend fun deleteGroupById(chamaId: Long)

    @Query("SELECT * FROM chamas WHERE id = :chamaId")
    suspend fun getBroupById(chamaId: Long): ChamaEntity?
}
