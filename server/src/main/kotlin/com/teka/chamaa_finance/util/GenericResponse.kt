package com.teka.chamaa_finance.util

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse<out T>(
    val isSuccess:Boolean,
    val message: String?,
    val data: T?
)
