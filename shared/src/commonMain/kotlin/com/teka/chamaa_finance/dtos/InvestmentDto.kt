package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvestmentDTO(
    val investmentId: Long,
    val memberId: Long,
    val chamaAccountId: Long,
    val investmentDescription: String,
    val investmentAmount: Double,
    val investmentDate: String
)
