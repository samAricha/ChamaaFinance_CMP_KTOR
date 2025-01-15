package com.teka.chamaa_finance.screens.trx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.teka.chamaa_finance.widgets.CustomButton

@Composable
fun CreateAccountsScreen(onCreateAccounts: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "This Demo app demonstrates the use of Kaptos. We create accounts and transfer funds between them.",
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.ExtraLight,
                color = Color.White,
            )

            CustomButton (
                modifier = Modifier.fillMaxWidth(),
                onClick = onCreateAccounts,
                btnText = "Create Accounts",
            )
        }
    }
}