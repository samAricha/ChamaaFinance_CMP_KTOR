package com.teka.chamaa_finance


import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import com.teka.chamaa_finance.navigation.NavGraph
import com.teka.chamaa_finance.ui.theme.AppTheme



@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()
    AppTheme {
        NavGraph(navController = navController)
    }
}