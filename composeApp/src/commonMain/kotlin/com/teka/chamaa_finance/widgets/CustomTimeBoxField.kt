package com.teka.chamaa_finance.widgets;

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.TextStyle
import com.teka.chamaa_finance.util.TextFieldStateMngr

@Composable
fun CustomTimeBoxField(
    modifier: Modifier = Modifier,
    labelText: String = "Time",
    editable: Boolean = false,
    isOptional: Boolean = false,
    currentTextState: String,
    onClick: () -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
) {
    Column(
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {
        CustomInputTextField(
            labelText = labelText,
            value = TextFieldStateMngr(currentTextState),
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.AvTimer,
                    contentDescription = "Timer icon",
                    tint = DarkGray
                )
            },
            editable = editable,
            isOptional = isOptional,
        )
    }
}