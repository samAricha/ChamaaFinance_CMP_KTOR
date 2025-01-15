package com.teka.chamaa_finance.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.teka.chamaa_finance.ui.theme.RajdhaniFontFamily
import com.teka.chamaa_finance.ui.theme.TextSizeMedium

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String = "",
    fontFamily: FontFamily = RajdhaniFontFamily(),
    fontWeight: FontWeight = FontWeight.Companion.Light,
    color: Color = DarkGray,
    fontSize: TextUnit = TextSizeMedium
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        color = color,
        fontSize = fontSize
    )

}