package com.teka.chamaa_finance.screens.group_members.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teka.chamaa_finance.navigation.AppDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsTabContent(
    navController: NavController,
) {

    Box(){
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                navController.navigate(AppDestinations.CreateGroupDestination.route)
            },
            icon = { Icon(Icons.Filled.Add, contentDescription = "Create Group") },
            text = { Text(text = "Create Group") },
        )
    }
}