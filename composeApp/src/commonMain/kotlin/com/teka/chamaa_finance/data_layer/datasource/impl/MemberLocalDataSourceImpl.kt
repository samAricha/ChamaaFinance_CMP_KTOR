package com.teka.chamaa_finance.data_layer.datasource.impl

import com.teka.chamaa_finance.data_layer.database.PeopleDatabase
import com.teka.chamaa_finance.data_layer.datasource.def.MemberLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.MemberEntity


class MemberLocalDataSourceImpl(private val appDatabase: PeopleDatabase) : MemberLocalDataSource {


    override suspend fun getAllMembers(): List<MemberEntity> {
        return appDatabase.memberDao().getAllMembers()
    }

    override suspend fun createMember(member: MemberEntity) {
        return appDatabase.memberDao().createMember(member)
    }

    override suspend fun deleteMember(id: Long) {
        return appDatabase.memberDao().deleteMemberById(id)
    }

    override suspend fun updateMember(member: MemberEntity) {
        return appDatabase.memberDao().updateMember(member)
    }

    override suspend fun getMember(id: Long): MemberEntity? {
        return appDatabase.memberDao().getMemberById(id)
    }
}