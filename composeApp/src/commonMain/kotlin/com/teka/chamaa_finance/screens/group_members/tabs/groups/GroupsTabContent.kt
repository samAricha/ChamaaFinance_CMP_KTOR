package com.teka.chamaa_finance.screens.group_members.tabs.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teka.chamaa_finance.navigation.AppDestinations
import com.teka.chamaa_finance.widgets.EmptyContent
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsTabContent(
    navController: NavController,
) {
    val viewModel = koinViewModel<GroupsTabViewModel>()
    val uiState = viewModel.groupsTabUiState.collectAsState().value
    val chamaList = uiState.chamaaList


    Box(modifier = Modifier.fillMaxSize()) {
        Column{
            if (chamaList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    itemsIndexed(chamaList) { index, chamaa ->
                        ChamaaItemCard(
                            chamaa = chamaa,
                            navController = navController
                        )
                    }
                }
            }
            if (chamaList.isEmpty()){
                EmptyContent(message = "No Groups")
            }
        }

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