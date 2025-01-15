package com.teka.chamaa_finance.screens.contribution.contribution_list

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.data_layer.entities.ContributionEntity


@Stable
data class ContributionListUiState(
    var contributionList: List<ContributionEntity> = emptyList(),
){
    companion object {
        val default = ContributionListUiState()
    }
}

