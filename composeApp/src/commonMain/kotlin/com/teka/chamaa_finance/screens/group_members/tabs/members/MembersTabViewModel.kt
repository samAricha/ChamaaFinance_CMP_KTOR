package com.teka.chamaa_finance.screens.group_members.tabs.members

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.repository_impl.MemberRepositoryImpl
import com.teka.chamaa_finance.screens.group_members.tabs.groups.GroupsTabUiState
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class MembersTabViewModel : ViewModel(), KoinComponent{
    private val memberRepository: MemberRepositoryImpl by inject()

    // UI state holder
    private val _membersTabUiState = MutableStateFlow(MembersTabUiState())
    val membersTabUiState: StateFlow<MembersTabUiState> = _membersTabUiState

    init {
        fetchMembers()
    }

    fun fetchMembers(){
        viewModelScope.launch{
            val members = memberRepository.getAllMembers()
            updateModelField(MembersTabUiState::membersList, members)
        }
    }



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<MembersTabUiState, T>, value: T) {
        _membersTabUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<MembersTabUiState, TextFieldStateMngr>, value: String) {
        _membersTabUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
