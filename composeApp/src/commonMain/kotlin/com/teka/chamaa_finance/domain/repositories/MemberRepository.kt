package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.data_layer.entities.MemberEntity

interface MemberRepository {
    suspend fun getAllMembers(): List<MemberEntity>
    suspend fun createMember(member: MemberEntity)
    suspend fun updateMember(member: MemberEntity)
    suspend fun deleteMember(id: Long)
    suspend fun getMember(id: Long): MemberEntity?
}