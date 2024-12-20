package com.teka.chamaa_finance.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeInfoCard(
    title: String,
    value: String,
    iconRes: DrawableResource,
    color: Color,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
) {
    val iconPainter = painterResource(iconRes)

    Card(
        modifier = modifier
            .padding(end = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = iconPainter,
                contentDescription = "",
                modifier = Modifier.size(75.dp)
            )
            Text(
                text = title,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}