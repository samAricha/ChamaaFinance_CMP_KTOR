package com.teka.chamaa_finance.screens.trx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teka.chamaa_finance.widgets.CustomButton
import xyz.mcxross.kaptos.account.Account
import com.teka.chamaa_finance.screens.trx.components.ShortenedAddress

@Composable
fun AccountDetails(
    alice: Account?,
    aliceBalance: Long,
    bob: Account?,
    bobBalance: Long,
    transferAmount: Int,
    onTransferAmountChange: (Int) -> Unit,
    onFundAlice: () -> Unit,
    onFundBob: () -> Unit,
    onTransfer: () -> Unit
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Row {
            Text("Alice:   ", fontSize = 16.sp, color = Color.White)
            alice?.let { ShortenedAddress(it.accountAddress.toString()) }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                "$aliceBalance",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

        Divider(modifier = Modifier.padding(10.dp), color = Color.DarkGray)

        Row {
            Text("Bob:   ", fontSize = 16.sp, color = Color.White)
            bob?.let { ShortenedAddress(it.accountAddress.toString()) }
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                "$bobBalance",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CustomButton(
                onClick = onFundAlice,
                btnText = "Fund Alice",
            )

            CustomButton(
                onClick = onFundBob,
                btnText = "Fund Bob",
            )

            Divider(modifier = Modifier.padding(10.dp), color = Color.DarkGray)

            TextField(
                value = transferAmount.toString(),
                onValueChange = { onTransferAmountChange(it.toIntOrNull() ?: 0) },
                textStyle = MaterialTheme.typography.bodyMedium,
                label = { Text("Transfer Amount", color = Color.White) },
                keyboardOptions =
                KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(10.dp),
            )

            CustomButton(
                onClick = onTransfer,
                btnText = "Transfer",
            )
        }
    }
}