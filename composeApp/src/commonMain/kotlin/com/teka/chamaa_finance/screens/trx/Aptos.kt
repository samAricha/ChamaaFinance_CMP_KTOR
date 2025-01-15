package com.teka.chamaa_finance.screens.trx;

import androidx.compose.runtime.Composable
import xyz.mcxross.kaptos.Aptos
import xyz.mcxross.kaptos.account.Account


import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import xyz.mcxross.kaptos.model.Option
import com.teka.chamaa_finance.screens.trx.model.transfer
import com.teka.chamaa_finance.screens.trx.components.BalanceRefresher

@Composable
fun AptosScreen() {

    val aptos = remember { Aptos() }

    val accountCreated = remember { mutableStateOf(false) }

    var alice by remember { mutableStateOf<Account?>(null) }
    var aliceBalance by remember { mutableStateOf(0L) }

    var bob by remember { mutableStateOf<Account?>(null) }
    var bobBalance by remember { mutableStateOf(0L) }

    var transferAmount by remember { mutableStateOf(0) }

    val coroutineScope by rememberUpdatedState(rememberCoroutineScope())

    // This state is used to trigger balance updates.
    val updateTrigger = remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = updateTrigger.intValue, key2 = accountCreated) {
        if (accountCreated.value) {
            aliceBalance = fetchBalance(aptos, alice)
            bobBalance = fetchBalance(aptos, bob)
        }
    }

    BalanceRefresher(updateTrigger)


        if (!accountCreated.value) {
            CreateAccountsScreen(
                onCreateAccounts = {
                    coroutineScope.launch {
                        alice = Account.generate()
                        alice?.accountAddress?.let {
                            aptos.fundAccount(it, 1_000_000_000)
                        }
                        bob = Account.generate()
                        bob?.accountAddress?.let {
                            aptos.fundAccount(it, 2_000_000_000)
                        }
                        accountCreated.value = true
                    }
                }
            )
        } else {
            AccountDetails(
                alice = alice,
                aliceBalance = aliceBalance,
                bob = bob,
                bobBalance = bobBalance,
                transferAmount = transferAmount,
                onTransferAmountChange = { transferAmount = it },
                onFundAlice = {
                    coroutineScope.launch {
                        alice?.let {
                            aptos.fundAccount(it.accountAddress, 1_000_000_000)
                        }
                    }
                },
                onFundBob = {
                    coroutineScope.launch {
                        bob?.let { aptos.fundAccount(it.accountAddress, 2_000_000_000) }
                    }
                },
                onTransfer = {
                    coroutineScope.launch {
                        try {
                            alice?.let {
                                bob?.let { bobAccount ->
                                    transfer(aptos, it, bobAccount, transferAmount)
                                    updateTrigger.value++
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            )
        }
}

suspend fun fetchBalance(aptos: Aptos, account: Account?): Long {
    return account?.let {
        when (val balance = aptos.getAccountAPTAmount(it.accountAddress)) {
            is Option.Some -> balance.value.div(100_000_000)
            is Option.None -> 0L
        }
    } ?: 0L
}