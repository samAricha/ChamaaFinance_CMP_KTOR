package com.teka.chamaa_finance.data_layer.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ChamaMembersDTO(
    @SerialName("chama_id")
    val chamaId: String,
    @SerialName("member_id")
    val memberId: String,
)
