package com.teka.chamaa_finance.util

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse<out T>(
    val isSuccess:Boolean,
    val data: T
)
