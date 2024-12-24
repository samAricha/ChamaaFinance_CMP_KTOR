package com.teka.chamaa_finance.screens.group_members.tabs.accounts

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.data_layer.entities.ChamaAccountEntity


@Stable
data class AccountsTabUiState(
    var chamaAccountList: List<ChamaAccountEntity> = emptyList(),
){
    companion object {
        val default = AccountsTabUiState()
    }
}

