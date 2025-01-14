package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChamaMembersDTO(
    val chamaId: String,
    val memberId: String,
)
