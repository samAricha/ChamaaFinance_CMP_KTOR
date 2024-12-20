package com.teka.chamaa_finance.screens.home

import androidx.compose.runtime.Stable
import com.teka.chamaa_finance.domain.models.UserData


@Stable
data class HomeScreenUIState(
    var modules: List<HomeModuleModel> = emptyList(),
    var userData: UserData? = null,
    var isAuthenticated: Boolean = false,
    var isPreparingModules: Boolean = false,
    var showLogoutDialog: Boolean = false,
    var showPasswordDialog: Boolean = false,
    var errorMessage: String? = null,
    var connectivityStatus: String? = null,
){
    companion object {
        val default = HomeScreenUIState()
    }
}