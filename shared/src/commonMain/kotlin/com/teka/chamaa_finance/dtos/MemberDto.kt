package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberDTO(
    val memberId: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val dateJoined: String = ""
)
