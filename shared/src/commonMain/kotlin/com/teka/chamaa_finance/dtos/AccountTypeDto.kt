package com.teka.chamaa_finance.dtos

import kotlinx.serialization.Serializable

@Serializable
data class AccountTypeDTO(
    val accountTypeId: Int = 0,
    val accountName: String,
    val created_at: String?,
    val updated_at: String?,
)
