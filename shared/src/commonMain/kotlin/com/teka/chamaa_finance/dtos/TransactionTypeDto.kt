package com.teka.chamaa_finance.dtos

import kotlinx.serialization.Serializable


@Serializable
data class TransactionTypeDTO(
    val transactionTypeId: Long,
    val transactionName: String,
)
