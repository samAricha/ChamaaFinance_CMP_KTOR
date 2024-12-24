package com.teka.chamaa_finance.screens.group_members.forms.create_group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.data_layer.repository_impl.GroupRepositoryImpl
import com.teka.chamaa_finance.domain.usecase.CreateNoteUseCase
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class CreateGroupViewModel : ViewModel(), KoinComponent{

    private val groupRepository: GroupRepositoryImpl by inject()

    // UI state holder
    private val _createGroupUiState = MutableStateFlow(CreateGroupUiState())
    val createGroupUiState: StateFlow<CreateGroupUiState> = _createGroupUiState


    fun saveGroup(){
        viewModelScope.launch{
            val chama = ChamaEntity(
                chamaName = createGroupUiState.value.groupName.text,
                chamaDescription = createGroupUiState.value.groupDescription.text,
                dateFormed = createGroupUiState.value.date.date.toString(),
            )
            groupRepository.createGroup(chama)
        }
    }



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
