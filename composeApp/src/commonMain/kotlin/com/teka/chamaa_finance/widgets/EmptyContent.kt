package com.teka.chamaa_finance.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import com.teka.chamaa_finance.ui.theme.TextSizeLarge
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier,
    imageRes: DrawableResource = Res.drawable.chamaa_logo_no_bg,
    message: String,
    imageSize: Dp = 120.dp,
    imageAlpha: Float = 0.3f,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    fontSize: TextUnit = TextSizeLarge,
    textAlign: TextAlign = TextAlign.Center,
    fontFamily: FontFamily = FontFamily.Cursive,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(contentAlignment),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Empty state illustration",
                modifier = Modifier.size(imageSize),
                alpha = imageAlpha
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message.toString(),
                style = textStyle,
                fontSize = fontSize,
                textAlign = textAlign,
                fontFamily = fontFamily
            )
        }
    }
}
