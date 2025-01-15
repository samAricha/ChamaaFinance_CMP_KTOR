package com.teka.chamaa_finance.data_layer.repository_impl

import com.teka.chamaa_finance.data_layer.datasource.def.MemberLocalDataSource
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.domain.repositories.MemberRepository


class MemberRepositoryImpl(
    private val memberLocalDatasource: MemberLocalDataSource
) : MemberRepository {

    override suspend fun getAllMembers(): List<MemberEntity> {
        return memberLocalDatasource.getAllMembers()
    }

    override suspend fun createMember(member: MemberEntity) {
        return memberLocalDatasource.createMember(member)
    }

    override suspend fun updateMember(member: MemberEntity) {
        return memberLocalDatasource.updateMember(member)
    }

    override suspend fun deleteMember(id: Long) {
        return memberLocalDatasource.deleteMember(id)
    }

    override suspend fun getMember(id: Long): MemberEntity? {
        return memberLocalDatasource.getMember(id)
    }

}