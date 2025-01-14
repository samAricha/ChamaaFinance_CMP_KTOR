package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDTO(
    val transactionId: Long,
    val memberId: Long,
    val chamaAccountId: Long,
    val transactionDate: String,
    val transactionType: String,
    val amount: Double
)
