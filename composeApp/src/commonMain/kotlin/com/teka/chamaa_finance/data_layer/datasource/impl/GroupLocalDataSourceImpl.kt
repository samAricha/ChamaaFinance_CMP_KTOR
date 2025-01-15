package com.teka.chamaa_finance.data_layer.datasource.impl

import com.teka.chamaa_finance.data_layer.database.PeopleDatabase
import com.teka.chamaa_finance.data_layer.datasource.def.GroupLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity


class GroupLocalDataSourceImpl(private val appDatabase: PeopleDatabase) : GroupLocalDataSource {

    override suspend fun getAllGroups(): List<ChamaEntity> {
       return appDatabase.groupDao().getAllGroups()
    }

    override suspend fun createGroup(group: ChamaEntity) {
        return appDatabase.groupDao().createGroup(group)
    }

    override suspend fun deleteGroup(id: Long) {
       return appDatabase.groupDao().deleteGroupById(id)
    }

    override suspend fun updateGroup(group: ChamaEntity) {
        return appDatabase.groupDao().updateGroup(group)
    }

    override suspend fun getGroup(id: Long): ChamaEntity? {
       return appDatabase.groupDao().getBroupById(id)
    }
}