package com.teka.chamaa_finance.screens.contribution.contribution_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.group
import com.teka.chamaa_finance.navigation.AppDestinations
import com.teka.chamaa_finance.widgets.CustomTopAppBar
import com.teka.chamaa_finance.widgets.EmptyContent
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContributionsScreen(
    navController: NavController,
) {

    val viewModel = koinViewModel<ContributionListViewModel>()
    val uiState = viewModel.contributionListUiState.collectAsState().value
    val contributionList = uiState.contributionList


    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            CustomTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Contributions",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            "",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {},
                        content = {
                            IconButton(onClick = {
                                navController.navigate(AppDestinations.AboutDestination.route)
                            }) {
                                Image(
                                    painter = painterResource(Res.drawable.group),
                                    contentDescription = "About Us",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { padding ->

        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                if (contributionList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        itemsIndexed(contributionList) { index, contribution ->
                            ContributionItemCard(
                                contribution = contribution,
                                navController = navController
                            )
                        }
                    }
                }

                if (contributionList.isEmpty()){
                    EmptyContent(message = "No Contributions")
                }
            }

            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = {
                    navController.navigate(AppDestinations.CreateContributionDestination.route)
                },
                icon = { Icon(Icons.Filled.Add, contentDescription = "Add Contribution") },
                text = { Text(text = "Add Contribution") },
            )
        }

    }
}