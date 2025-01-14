package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributionWithNamesDTO(
    val contributionId: String ="",
    val memberId: String = "",
    val chamaAccountId: String = "",
    val contributionDate: String = "",
    val contributionAmount: String = "",
    val firstName: String = "",
    val lastName: String = ""
)
