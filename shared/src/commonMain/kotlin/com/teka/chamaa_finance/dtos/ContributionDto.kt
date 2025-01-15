package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributionDTO(
    val contributionId: String ="",
    val chamaaId: String ="",
    val memberId: String = "",
    val chamaAccountId: String = "",
    val contributionDate: String = "",
    val contributionAmount: String = ""
)
