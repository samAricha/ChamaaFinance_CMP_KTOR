package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChamaDTO(
    @SerialName("id")
    val chamaId: String,
    @SerialName("chama_name")
    val chamaName: String,
    @SerialName("chama_description")
    val chamaDescription: String,
    @SerialName("date_formed")
    val dateFormed: String
)
