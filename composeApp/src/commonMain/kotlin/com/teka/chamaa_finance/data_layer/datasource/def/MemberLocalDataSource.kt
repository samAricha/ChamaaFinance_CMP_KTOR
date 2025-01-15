package com.teka.chamaa_finance.data_layer.datasource.def

import com.teka.chamaa_finance.data_layer.entities.MemberEntity

interface MemberLocalDataSource {
    suspend fun getAllMembers(): List<MemberEntity>
    suspend fun createMember(member: MemberEntity)
    suspend fun deleteMember(id: Long)
    suspend fun updateMember(member: MemberEntity)
    suspend fun getMember(id: Long): MemberEntity?
}