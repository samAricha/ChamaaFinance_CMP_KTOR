package com.teka.chamaa_finance.screens.contribution.contribution_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


class ContributionListViewModel : ViewModel(), KoinComponent{
    private val contributionRepository: ContributionRepositoryImpl by inject()

    // UI state holder
    private val _contributionListUiState = MutableStateFlow(ContributionListUiState())
    val contributionListUiState: StateFlow<ContributionListUiState> = _contributionListUiState

    init {
        fetchContributions()
    }

    fun fetchContributions(){
        viewModelScope.launch{
            val groups = contributionRepository.getAllContributions()
            updateModelField(ContributionListUiState::contributionList, groups)
        }
    }



    //////////////////// STATE UPDATES ///////////////////////
    fun <T> updateModelField(property: KMutableProperty1<ContributionListUiState, T>, value: T) {
        _contributionListUiState.update { currentState ->
            currentState.copy().also { newState ->
                property.set(newState, value)
            }
        }
    }
    fun updateStringField(property: KMutableProperty1<ContributionListUiState, TextFieldStateMngr>, value: String) {
        _contributionListUiState.update { currentState ->
            val updatedState = property.get(currentState).copy(text = value)
            currentState.copy().also {
                property.set(it, updatedState)
            }
        }
    }


}
