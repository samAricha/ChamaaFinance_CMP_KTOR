package com.teka.chamaa_finance.widgets;

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.DialogProperties
import kotlinx.datetime.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    title: String,
    state: TimePickerState,
    onDismiss: () -> Unit,
    onConfirmStartTime: (LocalTime) -> Unit,
    modifier: Modifier = Modifier,
    cancelButtonText: String = "Cancel",
    confirmButtonText: String = "OK",
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
) {
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = true),
        modifier = modifier,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = titleStyle,
            )
        },
        text = {
            TimeInput(
                modifier = Modifier.fillMaxWidth(),
                state = state,
            )
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                content = {
                    Text(text = cancelButtonText)
                },
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmStartTime(
                        LocalTime(
                            hour = state.hour,
                            minute = state.minute,
                        ),
                    )
                    onDismiss()
                },
                content = {
                    Text(text = confirmButtonText)
                },
            )
        },
    )
}
