package com.teka.chamaa_finance.screens.group_members.forms.create_member

import androidx.lifecycle.ViewModel
import com.teka.chamaa_finance.screens.group_members.forms.create_group.CreateGroupUiState
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import kotlin.reflect.KMutableProperty1


class CreateMemberViewModel : ViewModel(), KoinComponent{

    // UI state holder
    private val _createMemberUiState = MutableStateFlow(CreateMemberUiState())
    val createMemberUiState: StateFlow<CreateMemberUiState> = _createMemberUiState



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<CreateMemberUiState, T>, value: T) {
        _createMemberUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<CreateMemberUiState, TextFieldStateMngr>, value: String) {
        _createMemberUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
