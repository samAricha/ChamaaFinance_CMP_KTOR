package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoanDTO(
    val loanId: Long,
    val memberId: Long,
    val chamaAccountId: Long,
    val loanAmount: Double,
    val interestRate: Double,
    val loanDate: String,
    val loanStatus: String
)
