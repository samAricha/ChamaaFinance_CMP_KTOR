package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.dtos.MemberDTO
import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task

interface MemberRepository {
    suspend fun allMembers(): List<MemberDTO>
    suspend fun memberById(id: String): MemberDTO?
    suspend fun addMember(member: MemberDTO): MemberDTO
    suspend fun removeMember(id: String): Boolean
}