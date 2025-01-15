package com.teka.chamaa_finance.screens.group_members.tabs.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.repository_impl.AccountRepositoryImpl
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class AccountsTabViewModel : ViewModel(), KoinComponent{
    private val accountsRepository: AccountRepositoryImpl by inject()

    // UI state holder
    private val _accountsTabUiState = MutableStateFlow(AccountsTabUiState())
    val accountsTabUiState: StateFlow<AccountsTabUiState> = _accountsTabUiState

    init {
        fetchChamaAccounts()
    }

    fun fetchChamaAccounts(){
        viewModelScope.launch{
            val accounts = accountsRepository.getAllAccounts()
            updateModelField(AccountsTabUiState::chamaAccountList, accounts)
        }
    }



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<AccountsTabUiState, T>, value: T) {
        _accountsTabUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<AccountsTabUiState, TextFieldStateMngr>, value: String) {
        _accountsTabUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
