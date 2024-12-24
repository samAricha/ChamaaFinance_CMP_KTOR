package com.teka.chamaa_finance.screens.group_members.forms.create_contribution

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.util.TextFieldStateMngr
import com.teka.chamaa_finance.util.today
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime


@Stable
data class CreateContributionUiState(
    var firstName: TextFieldStateMngr = TextFieldStateMngr(labelText = "first name",),
    var lastName: TextFieldStateMngr = TextFieldStateMngr(labelText = "last name",),
    var phoneNumber: TextFieldStateMngr = TextFieldStateMngr(labelText = "phone number",),
    var email: TextFieldStateMngr = TextFieldStateMngr(labelText = "email",),
    var isSavingFormData: Boolean = false,
    var isFormSubmissionSuccessful: Boolean = false,
    var showDatePicker: Boolean = false,
    var showTimePicker: Boolean = false,
    var date: LocalDateTime = today(),
    var time: LocalTime = today().time,
    var errorMessage: String = "",
){
    companion object {
        val default = CreateContributionUiState()
    }
}

