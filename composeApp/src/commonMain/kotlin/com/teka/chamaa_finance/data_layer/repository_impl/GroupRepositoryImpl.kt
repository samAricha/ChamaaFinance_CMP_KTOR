package com.teka.chamaa_finance.data_layer.repository_impl

import com.teka.chamaa_finance.data_layer.datasource.def.GroupLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.domain.repositories.GroupRepository


class GroupRepositoryImpl(
    private val groupLocalDataSource: GroupLocalDataSource
) : GroupRepository {

    override suspend fun getAllGroups(): List<ChamaEntity> {
        return groupLocalDataSource.getAllGroups()
    }

    override suspend fun createGroup(group: ChamaEntity) {
        return groupLocalDataSource.createGroup(group)
    }

    override suspend fun updateGroup(group: ChamaEntity) {
        return groupLocalDataSource.updateGroup(group)
    }

    override suspend fun deleteGroup(id: Long) {
        return groupLocalDataSource.deleteGroup(id)
    }

    override suspend fun getGroup(id: Long): ChamaEntity? {
        return groupLocalDataSource.getGroup(id)
    }

}