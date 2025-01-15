package com.teka.chamaa_finance.screens.home.components

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class HomeInfoCardData(
    val title: String,
    val value: String,
    val iconRes: DrawableResource,
    val color: Color,
    val onClick: ()->Unit
)