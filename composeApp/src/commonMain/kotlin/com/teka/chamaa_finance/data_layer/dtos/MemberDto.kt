package com.teka.chamaa_finance.data_layer.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberDTO(
    @SerialName("id")
    val memberId: String = "",
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("phone")
    val phone: String = "",
    @SerialName("date_joined")
    val dateJoined: String = ""
)
