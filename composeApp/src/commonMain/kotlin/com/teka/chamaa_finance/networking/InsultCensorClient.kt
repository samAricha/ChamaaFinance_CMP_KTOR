package com.teka.chamaa_finance.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import com.teka.chamaa_finance.networking.util.NetworkError
import com.teka.chamaa_finance.networking.util.NetworkResult

class InsultCensorClient(
    private val httpClient: HttpClient
) {

    suspend fun censorWords(uncensored: String): NetworkResult<String, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://www.purgomalum.com/service/json"
            ) {
                parameter("text", uncensored)
            }
        } catch(e: UnresolvedAddressException) {
            return NetworkResult.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return NetworkResult.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val censoredText = response.body<CensoredText>()
                NetworkResult.Success(censoredText.result)
            }
            401 -> NetworkResult.Error(NetworkError.UNAUTHORIZED)
            409 -> NetworkResult.Error(NetworkError.CONFLICT)
            408 -> NetworkResult.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> NetworkResult.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> NetworkResult.Error(NetworkError.SERVER_ERROR)
            else -> NetworkResult.Error(NetworkError.UNKNOWN)
        }
    }
}