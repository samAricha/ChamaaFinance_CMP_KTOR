package com.teka.chamaa_finance.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.rajdhani_bold
import chamaafinance.composeapp.generated.resources.rajdhani_light
import chamaafinance.composeapp.generated.resources.rajdhani_medium
import chamaafinance.composeapp.generated.resources.rajdhani_regular
import chamaafinance.composeapp.generated.resources.rajdhani_semi_bold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RajdhaniFontFamily() = FontFamily(
    Font(Res.font.rajdhani_light, weight = FontWeight.Light),
    Font(Res.font.rajdhani_regular, weight = FontWeight.Normal),
    Font(Res.font.rajdhani_medium, weight = FontWeight.Medium),
    Font(Res.font.rajdhani_semi_bold, weight = FontWeight.SemiBold),
    Font(Res.font.rajdhani_bold, weight = FontWeight.Bold)
)



@Composable
fun RajdhaniTypography() = Typography().run {
    val customType = RajdhaniFontFamily()


    copy(
        displayLarge = displayLarge.copy(fontFamily = customType),
        displayMedium = displayMedium.copy(fontFamily = customType),
        displaySmall = displaySmall.copy(fontFamily = customType),
        headlineLarge = headlineLarge.copy(fontFamily = customType),
        headlineMedium = headlineMedium.copy(fontFamily = customType),
        headlineSmall = headlineSmall.copy(fontFamily = customType),
        titleLarge = titleLarge.copy(fontFamily = customType),
        titleMedium = titleMedium.copy(fontFamily = customType),
        titleSmall = titleSmall.copy(fontFamily = customType),
        bodyLarge = bodyLarge.copy(fontFamily = customType),
        bodyMedium = bodyMedium.copy(fontFamily = customType),
        bodySmall = bodySmall.copy(fontFamily = customType),
        labelLarge = labelLarge.copy(fontFamily = customType),
        labelMedium = labelMedium.copy(fontFamily = customType),
        labelSmall = labelSmall.copy(fontFamily = customType)
    )
}