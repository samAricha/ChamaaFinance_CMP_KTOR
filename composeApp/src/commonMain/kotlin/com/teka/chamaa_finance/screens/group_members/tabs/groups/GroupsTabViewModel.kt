package com.teka.chamaa_finance.screens.group_members.tabs.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.repository_impl.GroupRepositoryImpl
import com.teka.chamaa_finance.screens.group_members.forms.create_member.CreateMemberUiState
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class GroupsTabViewModel : ViewModel(), KoinComponent{
    private val groupRepository: GroupRepositoryImpl by inject()

    // UI state holder
    private val _groupsTabUiState = MutableStateFlow(GroupsTabUiState())
    val groupsTabUiState: StateFlow<GroupsTabUiState> = _groupsTabUiState

    init {
        fetchChamaas()
    }

    fun fetchChamaas(){
        viewModelScope.launch{
            val groups = groupRepository.getAllGroups()
            updateModelField(GroupsTabUiState::chamaaList, groups)
        }
    }



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<GroupsTabUiState, T>, value: T) {
        _groupsTabUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<GroupsTabUiState, TextFieldStateMngr>, value: String) {
        _groupsTabUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
