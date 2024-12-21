package com.teka.chamaa_finance.data_layer.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TransactionTypeDTO(
    @SerialName("id")
    val transactionTypeId: Long,
    @SerialName("name")
    val transactionName: String,
)
