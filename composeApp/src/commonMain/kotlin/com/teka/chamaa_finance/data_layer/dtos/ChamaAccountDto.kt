package com.teka.chamaa_finance.data_layer.dtos

import kotlinx.serialization.Serializable


@Serializable
data class ChamaAccountDTO(
    val chamaAccountId: String,
    val chamaId: String,
    val accountName: String,
    val accountTypeId: String,
    val dateFormed: String,
)
