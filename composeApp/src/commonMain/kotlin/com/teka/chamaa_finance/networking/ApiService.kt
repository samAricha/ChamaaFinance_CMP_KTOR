package com.teka.chamaa_finance.networking;

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiService(private val httpClient: HttpClient) {

    suspend fun getCensoredText(
        url: String,
        parameters: Map<String, String> = emptyMap()
    ): HttpResponse {
        return httpClient.get(url) {
            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }

    suspend fun post(
        url: String,
        body: Any,
        headers: Map<String, String> = emptyMap()
    ): HttpResponse {
        return httpClient.post(url) {
            headers.forEach { (key, value) ->
                header(key, value)
            }
            setBody(body)
        }
    }
}
