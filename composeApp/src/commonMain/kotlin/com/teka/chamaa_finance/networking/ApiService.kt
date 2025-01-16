package com.teka.chamaa_finance.networking;

import com.teka.chamaa_finance.dtos.ApiResponseHandler
import com.teka.chamaa_finance.networking.util.NetworkError
import com.teka.chamaa_finance.networking.util.NetworkResult
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpMethod
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

class ApiService(
    private val httpClient: HttpClient,
    private val baseUrl: String
) {

    suspend fun getCensoredText(
        endpoint: String,
        parameters: Map<String, String> = emptyMap()
    ): HttpResponse {
        return httpClient.get("$baseUrl$endpoint") {
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




    suspend fun <T> request(
        endpoint: String,
        method: HttpMethod,
        parameters: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap(),
        body: Any? = null,
        serializer: KSerializer<T>
    ): NetworkResult<T, NetworkError> {
        return try {
            val response: HttpResponse = httpClient.request("$baseUrl$endpoint") {
                this.method = method
                headers.forEach { (key, value) -> header(key, value) }
                parameters.forEach { (key, value) -> parameter(key, value) }
                if (body != null) setBody(body)
            }
            handleResponse(response, serializer)
        } catch (e: UnresolvedAddressException) {
            NetworkResult.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            NetworkResult.Error(NetworkError.SERIALIZATION)
        } catch (e: ClientRequestException) {
            handleClientError(e.response.status.value)
        }
    }


    private suspend fun <T> handleResponse(
        response: HttpResponse,
        serializer: KSerializer<T>
    ): NetworkResult<T, NetworkError> {
        return when (response.status.value) {
            in 200..299 -> {
                val responseBodyText = response.bodyAsText()
                val responseBody: T = Json.decodeFromString(serializer, responseBodyText)
                NetworkResult.Success(responseBody)
            }
            else -> handleClientError(response.status.value)
        }
    }



    private fun <T> handleClientError(statusCode: Int): NetworkResult<T, NetworkError> {
        return when (statusCode) {
            401 -> NetworkResult.Error(NetworkError.UNAUTHORIZED)
            409 -> NetworkResult.Error(NetworkError.CONFLICT)
            408 -> NetworkResult.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> NetworkResult.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> NetworkResult.Error(NetworkError.SERVER_ERROR)
            else -> NetworkResult.Error(NetworkError.UNKNOWN)
        }
    }
}
