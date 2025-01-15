package com.teka.chamaa_finance.screens.group_members.tabs.groups

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.data_layer.entities.ChamaEntity
import com.teka.chamaa_finance.util.today
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@Stable
data class GroupsTabUiState(
    var chamaaList: List<ChamaEntity> = emptyList(),
){
    companion object {
        val default = GroupsTabUiState()
    }
}

