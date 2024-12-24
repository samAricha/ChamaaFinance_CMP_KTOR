package com.teka.chamaa_finance.data_layer.datasource

import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.data_layer.entities.NoteEntity

interface GroupLocalDataSource {
    suspend fun getAllGroups(): List<ChamaEntity>
    suspend fun createGroup(group: ChamaEntity)
    suspend fun deleteGroup(id: Long)
    suspend fun updateGroup(group: ChamaEntity)
    suspend fun getGroup(id: Long): ChamaEntity?
}