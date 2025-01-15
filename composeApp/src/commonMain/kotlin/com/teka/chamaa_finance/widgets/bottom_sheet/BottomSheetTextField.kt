package com.teka.chamaa_finance.widgets.bottom_sheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import com.teka.chamaa_finance.util.TextFieldStateMngr
import com.teka.chamaa_finance.widgets.CustomInputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetTextField(
    modifier: Modifier = Modifier,
    labelText: String = "",
    placeholderText: String = "",
    currentTextState: String,
    editable: Boolean = false,
    error: String? = null,
    isOptional: Boolean = true,
    onClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .clickable {
                expanded = !expanded
                onClick()
            }
    ) {
        CustomInputTextField(
            labelText = labelText,
            value = TextFieldStateMngr(currentTextState),
            onValueChange = {},
            trailingIcon = {
                val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown
                Icon(
                    imageVector = icon,
                    contentDescription = "Valid",
                    tint = DarkGray
                )
            },
            editable = editable,
            isOptional = isOptional,
        )
    }

}
