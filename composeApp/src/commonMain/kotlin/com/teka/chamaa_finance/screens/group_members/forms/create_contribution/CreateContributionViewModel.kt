package com.teka.chamaa_finance.screens.group_members.forms.create_contribution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teka.chamaa_finance.data_layer.entities.ContributionEntity
import com.teka.chamaa_finance.data_layer.entities.MemberEntity
import com.teka.chamaa_finance.data_layer.repository_impl.ContributionRepositoryImpl
import com.teka.chamaa_finance.util.TextFieldStateMngr
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue
import kotlin.reflect.KMutableProperty1


class CreateContributionViewModel : ViewModel(), KoinComponent{

    private val contributionRepository: ContributionRepositoryImpl by inject()


    // UI state holder
    private val _createContributionUiState = MutableStateFlow(CreateContributionUiState())
    val createContributionUiState: StateFlow<CreateContributionUiState> = _createContributionUiState


    fun saveContribution(){
        viewModelScope.launch{
            val member = ContributionEntity(
                memberId = createContributionUiState.value.firstName.text,
                chamaAccountId = createContributionUiState.value.lastName.text,
                contributionAmount = createContributionUiState.value.phoneNumber.text,
                contributionDate = createContributionUiState.value.date.date.toString(),
            )
            contributionRepository.createContribution(member)
        }
    }



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<CreateContributionUiState, T>, value: T) {
        _createContributionUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<CreateContributionUiState, TextFieldStateMngr>, value: String) {
        _createContributionUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
