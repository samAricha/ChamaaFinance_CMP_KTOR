package com.teka.chamaa_finance.screens.group_members.forms.create_member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.data_layer.repository_impl.MemberRepositoryImpl
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class CreateMemberViewModel : ViewModel(), KoinComponent{

    private val memberRepository: MemberRepositoryImpl by inject()


    // UI state holder
    private val _createMemberUiState = MutableStateFlow(CreateMemberUiState())
    val createMemberUiState: StateFlow<CreateMemberUiState> = _createMemberUiState


    fun saveMember(){
        viewModelScope.launch{
            val member = MemberEntity(
                firstName = createMemberUiState.value.firstName.text,
                lastName = createMemberUiState.value.lastName.text,
                phone = createMemberUiState.value.phoneNumber.text,
                dateJoined = createMemberUiState.value.date.date.toString(),
            )
            memberRepository.createMember(member)
        }
    }



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
