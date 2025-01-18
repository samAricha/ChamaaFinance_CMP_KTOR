package com.teka.chamaa_finance.dtos

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MemberDTO(
    val memberId: String = UUID.randomUUID().toString(),
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val dateJoined: String = ""
)
