package com.teka.chamaa_finance.screens.group_members.forms.create_group

import androidx.lifecycle.ViewModel
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import kotlin.reflect.KMutableProperty1


class CreateGroupViewModel : ViewModel(), KoinComponent{

    // UI state holder
    private val _createGroupUiState = MutableStateFlow(CreateGroupUiState())
    val createGroupUiState: StateFlow<CreateGroupUiState> = _createGroupUiState



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<CreateGroupUiState, T>, value: T) {
        _createGroupUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<CreateGroupUiState, TextFieldStateMngr>, value: String) {
        _createGroupUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
