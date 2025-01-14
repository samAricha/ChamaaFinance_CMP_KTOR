package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.dtos.ChamaMembersDTO
import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task

interface ChamaMembersRepository {
    suspend fun allChamaaMembers(): List<ChamaMembersDTO>
    suspend fun chamaaMemberById(name: String): ChamaMembersDTO?
    suspend fun addChamaaMember(chamaaMember: ChamaMembersDTO): ChamaMembersDTO
    suspend fun removeChamaaMember(name: String): Boolean
}