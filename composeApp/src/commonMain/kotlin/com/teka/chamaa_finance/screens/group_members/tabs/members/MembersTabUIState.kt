package com.teka.chamaa_finance.screens.group_members.tabs.members

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.data_layer.entities.MemberEntity


@Stable
data class MembersTabUiState(
    var membersList: List<MemberEntity> = emptyList(),
){
    companion object {
        val default = MembersTabUiState()
    }
}

