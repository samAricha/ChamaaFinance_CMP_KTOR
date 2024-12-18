package com.teka.chamaa_finance

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.teka.chamaa_finance.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ChamaaFinance",
        ) {
            App()
        }
    }
}