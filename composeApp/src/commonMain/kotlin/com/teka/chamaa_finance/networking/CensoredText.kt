package com.teka.chamaa_finance.networking

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)
