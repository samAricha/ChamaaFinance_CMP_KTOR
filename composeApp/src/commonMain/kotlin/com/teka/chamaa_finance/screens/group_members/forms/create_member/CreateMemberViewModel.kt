package com.teka.chamaa_finance.screens.group_members.forms.create_member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.data_layer.entities.toEntity
import com.teka.chamaa_finance.data_layer.repository_impl.MemberRepositoryImpl
import com.teka.chamaa_finance.dtos.MemberDTO
import com.teka.chamaa_finance.networking.InsultCensorClient
import com.teka.chamaa_finance.networking.util.NetworkResult
import com.teka.chamaa_finance.networking.util.onError
import com.teka.chamaa_finance.networking.util.onSuccess
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import sun.rmi.runtime.Log
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class CreateMemberViewModel : ViewModel(), KoinComponent{
    private val log = Logger.withTag("CreateMemberViewModel")
    private val memberRepository: MemberRepositoryImpl by inject()
    val insultCensorClient: InsultCensorClient by inject()



    // UI state holder
    private val _createMemberUiState = MutableStateFlow(CreateMemberUiState())
    val createMemberUiState: StateFlow<CreateMemberUiState> = _createMemberUiState


    fun saveMember(){
        _createMemberUiState.value = _createMemberUiState.value.copy(isSavingFormData = true)

        val state = _createMemberUiState.value

        val member = MemberDTO(
            firstName = state.firstName.text,
            lastName = state.lastName.text,
            phone = state.phoneNumber.text,
            dateJoined = state.date.date.toString(),
        )

        viewModelScope.launch{
            val result = insultCensorClient.addMember(member)

            _createMemberUiState.value = when (result) {
                is NetworkResult.Success -> {
                    val data: MemberDTO = result.data
                    log.i ( "data sent successfully:: $data" )
                    memberRepository.createMember(data.toEntity())

                    _createMemberUiState.value.copy(
                        isSavingFormData = false,
                        isFormSubmissionSuccessful = true,
                        errorMessage = ""
                    )
                }

                is NetworkResult.Error -> {
                    log.e("saving failed:: ${result.message}")
                    _createMemberUiState.value.copy(
                        isSavingFormData = false,
                        isFormSubmissionSuccessful = false,
                        errorMessage = result.message ?: "An error occurred"
                    )
                }
            }

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
