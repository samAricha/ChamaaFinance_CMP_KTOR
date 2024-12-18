package com.teka.chamaa_finance.navigation

sealed class AppDestinations(val route: String) {
    object Home : AppDestinations("home_page")
    object CreateNotes : AppDestinations("create_note")
    object EditNotes : AppDestinations("edit_note")
}