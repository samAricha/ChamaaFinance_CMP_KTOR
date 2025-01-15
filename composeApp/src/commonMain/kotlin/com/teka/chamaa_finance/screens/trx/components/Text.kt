package com.teka.chamaa_finance.screens.trx.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ShortenedAddress(address: String) {
  Text(
    text = "${address.take(14)}...${address.takeLast(14)}",
    color = Color.White,
    fontSize = 16.sp,
  )
}
