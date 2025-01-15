package com.teka.chamaa_finance.widgets;

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.teka.chamaa_finance.util.selectedDateMillisToLocalDateTime
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    datePickerState: DatePickerState,
    onDismiss: () -> Unit,
    onConfirmDate: (LocalDateTime) -> Unit,
    cancelButtonText: String = "Cancel", // Default text for cancel button
    confirmButtonText: String = "OK", // Default text for confirm button
) {
    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = cancelButtonText)
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        onConfirmDate(millis.selectedDateMillisToLocalDateTime())
                    }
                    onDismiss()
                },
            ) {
                Text(text = confirmButtonText)
            }
        },
    ) {
        DatePicker(state = datePickerState)
    }
}
