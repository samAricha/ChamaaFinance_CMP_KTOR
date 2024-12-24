package com.teka.chamaa_finance.screens.group_members.forms.create_account

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.util.TextFieldStateMngr
import com.teka.chamaa_finance.util.today
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@Stable
data class CreateAccountUiState(
    var groupName: TextFieldStateMngr = TextFieldStateMngr(labelText = "group name",),
    var groupDescription: TextFieldStateMngr = TextFieldStateMngr(labelText = "group description",),
    var isSavingFormData: Boolean = false,
    var isFormSubmissionSuccessful: Boolean = false,
    var showDatePicker: Boolean = false,
    var showTimePicker: Boolean = false,
    var date: LocalDateTime = today(),
    var time: LocalTime = today().time,
    var errorMessage: String = "",
){
    companion object {
        val default = CreateAccountUiState()
    }
}

