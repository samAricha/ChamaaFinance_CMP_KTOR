package com.teka.chamaa_finance.screens.trx.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BalanceRefresher(updateTrigger: MutableState<Int>) {
  val coroutineScope = rememberCoroutineScope()

  DisposableEffect(Unit) {
    val job =
      coroutineScope.launch {
        while (true) {
          delay(5000)
          updateTrigger.value += 1
        }
      }
    onDispose { job.cancel() }
  }
}
