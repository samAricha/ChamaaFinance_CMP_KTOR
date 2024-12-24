package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.data_layer.entities.ChamaEntity

interface GroupRepository {
    suspend fun getAllGroups(): List<ChamaEntity>
    suspend fun createGroup(note: ChamaEntity)
    suspend fun updateGroup(note: ChamaEntity)
    suspend fun deleteGroup(id: Long)
    suspend fun getGroup(id: Long): ChamaEntity?
}