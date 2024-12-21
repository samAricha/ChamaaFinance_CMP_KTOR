package com.teka.chamaa_finance.data_layer.dtos

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponseHandler<T>(
    val isSuccessful: Boolean,
    val status: String?,
    val message: String?,
    val data: T?
)
