package com.teka.chamaa_finance.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContributionWithNamesDTO(
    @SerialName("id")
    val contributionId: String ="",
    @SerialName("member_id")
    val memberId: String = "",
    @SerialName("chamaa_account_id")
    val chamaAccountId: String = "",
    @SerialName("contribution_date")
    val contributionDate: String = "",
    @SerialName("contribution_amount")
    val contributionAmount: String = "",
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("lastName")
    val lastName: String = ""
)
