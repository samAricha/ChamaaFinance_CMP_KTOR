package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.data_layer.entities.ChamaEntity

interface GroupRepository {
    suspend fun getAllGroups(): List<ChamaEntity>
    suspend fun createGroup(group: ChamaEntity)
    suspend fun updateGroup(group: ChamaEntity)
    suspend fun deleteGroup(id: Long)
    suspend fun getGroup(id: Long): ChamaEntity?
}