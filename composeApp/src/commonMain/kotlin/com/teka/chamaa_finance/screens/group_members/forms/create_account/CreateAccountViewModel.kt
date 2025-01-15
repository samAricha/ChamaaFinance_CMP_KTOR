package com.teka.chamaa_finance.screens.group_members.forms.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity
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


class CreateAccountViewModel : ViewModel(), KoinComponent{

    private val groupRepository: AccountRepositoryImpl by inject()

    // UI state holder
    private val _createGroupUiState = MutableStateFlow(CreateAccountUiState())
    val createGroupUiState: StateFlow<CreateAccountUiState> = _createGroupUiState


    fun saveGroup(){
        viewModelScope.launch{
            val chamaAccount = ChamaAccountEntity(
                accountName = createGroupUiState.value.groupName.text,
                accountTypeId = createGroupUiState.value.groupName.text,
                chamaId = createGroupUiState.value.groupName.text,
                dateFormed = createGroupUiState.value.date.date.toString(),
            )
            groupRepository.createAccount(chamaAccount)
        }
    }



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<CreateAccountUiState, T>, value: T) {
        _createGroupUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<CreateAccountUiState, TextFieldStateMngr>, value: String) {
        _createGroupUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
