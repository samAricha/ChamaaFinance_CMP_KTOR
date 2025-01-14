package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChamaDTO(
    val chamaId: String,
    val chamaName: String,
    val chamaDescription: String,
    val dateFormed: String
)
