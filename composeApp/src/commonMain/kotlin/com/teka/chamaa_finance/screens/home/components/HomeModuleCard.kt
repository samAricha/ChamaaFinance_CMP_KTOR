package com.teka.chamaa_finance.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import com.teka.chamaa_finance.ui.theme.appShapes
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeModuleCard(
    title: String,
    iconRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
//            .shadow(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.chamaa_logo_no_bg),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(35.dp)
            )
            
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}


@Composable
fun HomeModuleCard(
    title: String,
    iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard (
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = appShapes.medium,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(Res.drawable.chamaa_logo_no_bg),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(58.dp)
            )
            Text(
                text = title,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

//            Text(
//                text = title,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                style = MaterialTheme.typography.bodyMedium,
//                modifier = Modifier.weight(1f),
//                fontWeight = FontWeight.Bold,
//                color = DarkGray
//            )
        }
    }
}


