package com.teka.chamaa_finance.domain.repositories

import com.teka.chamaa_finance.dtos.ChamaDTO
import com.teka.chamaa_finance.model.Priority
import com.teka.chamaa_finance.model.Task

interface ChamaaRepository {
    suspend fun allChamaas(): List<ChamaDTO>
    suspend fun chamaaById(name: String): ChamaDTO?
    suspend fun addChamaa(chamaa: ChamaDTO): ChamaDTO
    suspend fun removeChamaa(name: String): Boolean
}