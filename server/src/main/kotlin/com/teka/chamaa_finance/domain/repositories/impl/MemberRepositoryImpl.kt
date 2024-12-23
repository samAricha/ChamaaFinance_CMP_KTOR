package com.teka.chamaa_finance.domain.repositories.impl

import com.teka.chamaa_finance.domain.repositories.MemberRepository
import com.teka.chamaa_finance.dtos.MemberDTO

class MemberRepositoryImpl: MemberRepository {
    override suspend fun allMembers(): List<MemberDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun memberById(id: String): MemberDTO? {
        TODO("Not yet implemented")
    }

    override suspend fun addMember(member: MemberDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun removeMember(id: String): Boolean {
        TODO("Not yet implemented")
    }
}