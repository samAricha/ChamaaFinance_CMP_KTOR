package com.teka.chamaa_finance

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ChamaaFinance",
    ) {
        App()
    }
}