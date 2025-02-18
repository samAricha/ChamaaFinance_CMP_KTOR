package com.teka.chamaa_finance.screens.group_members

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chama_base_logo_nobg
import chamaafinance.composeapp.generated.resources.group
import com.teka.chamaa_finance.navigation.AppDestinations
import com.teka.chamaa_finance.screens.group_members.tabs.accounts.AccountsTabContent
import com.teka.chamaa_finance.screens.group_members.tabs.groups.GroupsTabContent
import com.teka.chamaa_finance.screens.group_members.tabs.members.MembersTabContent
import com.teka.chamaa_finance.widgets.CustomTopAppBar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupMembersScreen(
    navController: NavController,
) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

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
                            text = "Groups & Accounts",
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
                            tint = MaterialTheme.colorScheme.primary
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Tabs.entries.forEachIndexed { index, currentTab ->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = { Text(text = currentTab.text) }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (Tabs.entries[selectedTabIndex.value]) {
                    Tabs.Groups -> GroupsTabContent(navController = navController)
                    Tabs.Members -> MembersTabContent(navController = navController)
                    Tabs.Accounts -> AccountsTabContent(navController = navController)
                }

            }
        }
    }
}


enum class Tabs(val text: String) {
    Members("Members"),
    Groups("Groups"),
    Accounts("Accounts"),
}