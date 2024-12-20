package com.teka.chamaa_finance.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoadingScreen(
    logoResId: DrawableResource = Res.drawable.chamaa_logo_no_bg,
    modifier: Modifier = Modifier,
    logoSize: Dp = 100.dp,
    logoAlpha: Float = 0.2f
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Companion.Center
    ) {
        Image(
            painter = painterResource(logoResId),
            contentDescription = null,
            modifier = Modifier
                .size(logoSize)
                .alpha(logoAlpha)
        )
        CircularProgressIndicator()
    }
}
