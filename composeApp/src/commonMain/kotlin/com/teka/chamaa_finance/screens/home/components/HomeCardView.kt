package com.teka.chamaa_finance.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeCardView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(vertical = 5.dp, horizontal = 3.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.chamaa_logo_no_bg),
                contentDescription = "Home Image",
                contentScale = ContentScale.Fit
            )
        }
    }
}
