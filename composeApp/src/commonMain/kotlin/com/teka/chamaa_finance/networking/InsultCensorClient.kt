package com.teka.chamaa_finance.networking

import com.teka.chamaa_finance.dtos.ApiResponseHandler
import com.teka.chamaa_finance.dtos.MemberDTO
import com.teka.chamaa_finance.networking.util.AppEndpoints.BACKEND_SERVICE
import com.teka.chamaa_finance.networking.util.AppEndpoints.CENSOR_SERVICE
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import com.teka.chamaa_finance.networking.util.NetworkError
import com.teka.chamaa_finance.networking.util.NetworkResult
import io.ktor.http.HttpMethod
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.get


class InsultCensorClient(
    private val httpClient: HttpClient,
    private val apiService: ApiService = get(ApiService::class.java, named(CENSOR_SERVICE)),
    private val apiService2: ApiService = get(ApiService::class.java, named(BACKEND_SERVICE))
) {

    suspend fun censorWords2(uncensored: String): NetworkResult<String, NetworkError> {
        val response = try {
            apiService.getCensoredText(
                endpoint = "service/json",
                parameters = mapOf("text" to uncensored)
            )

//            httpClient.get(
//                urlString = "https://www.purgomalum.com/service/json"
//            ) {
//                parameter("text", uncensored)
//            }
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

    suspend fun censorWords(uncensored: String): NetworkResult<String, NetworkError> {
        return try {
            val result: NetworkResult<CensoredText, NetworkError> = apiService.request(
                endpoint = "service/json",
                method = HttpMethod.Get,
                parameters = mapOf("text" to uncensored),
                serializer = CensoredText.serializer()
            )

            when (result) {
                is NetworkResult.Success -> NetworkResult.Success(result.data.result)
                is NetworkResult.Error -> result
            }
        } catch (e: UnresolvedAddressException) {
            NetworkResult.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            NetworkResult.Error(NetworkError.SERIALIZATION)
        }
    }



    suspend fun addMember(member: MemberDTO): NetworkResult<MemberDTO, NetworkError> {
        return try {
            val result: NetworkResult<ApiResponseHandler<MemberDTO>, NetworkError> = apiService2.request(
                endpoint = "members",
                method = HttpMethod.Post,
                body = member,
                serializer = ApiResponseHandler.serializer(MemberDTO.serializer())
            )

            when (result) {
                is NetworkResult.Success -> {
                    if (result.data.isSuccessful) {
                        NetworkResult.Success(result.data.data!!)
                    } else {
                        NetworkResult.Error(NetworkError.SERVER_ERROR, result.data.message)
                    }
                }
                is NetworkResult.Error -> result
            }
        } catch (e: UnresolvedAddressException) {
            NetworkResult.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            NetworkResult.Error(NetworkError.SERIALIZATION)
        }
    }



}

