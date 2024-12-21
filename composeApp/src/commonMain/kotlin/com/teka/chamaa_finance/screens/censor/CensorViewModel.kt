package com.teka.chamaa_finance.screens.censor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.networking.InsultCensorClient
import com.teka.chamaa_finance.networking.util.NetworkError
import com.teka.chamaa_finance.networking.util.onError
import com.teka.chamaa_finance.networking.util.onSuccess
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue


class CensorViewModel : ViewModel(), KoinComponent {
    val insultCensorClient: InsultCensorClient by inject()

    var censoredText  = mutableStateOf<String?>(null)

    var isLoading = mutableStateOf(false)

    var errorMessage =  mutableStateOf<NetworkError?>(null)


    fun censorText(uncensoredText: String){
        viewModelScope.launch {
            insultCensorClient.censorWords(uncensoredText)
                .onSuccess {
                    censoredText.value = it
                }
                .onError {
                    errorMessage.value = it
                }
                isLoading.value = false
        }
    }


}
