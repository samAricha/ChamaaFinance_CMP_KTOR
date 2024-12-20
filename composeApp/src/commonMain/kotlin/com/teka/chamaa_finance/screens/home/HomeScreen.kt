package com.teka.chamaa_finance.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chamaa_logo_no_bg
import chamaafinance.composeapp.generated.resources.customers
import chamaafinance.composeapp.generated.resources.stock
import chamaafinance.composeapp.generated.resources.stock_mngnt
import com.teka.chamaa_finance.domain.models.UserData
import com.teka.chamaa_finance.screens.home.components.FeaturedBox
import com.teka.chamaa_finance.screens.home.components.HomeInfoCard
import com.teka.chamaa_finance.screens.home.components.HomeInfoCardData
import com.teka.chamaa_finance.ui.theme.PrimaryGreen
import com.teka.chamaa_finance.ui.theme.TextSizeLarge
import com.teka.chamaa_finance.ui.theme.TextSizeXLarge
import com.teka.chamaa_finance.widgets.CustomTopAppBar
import com.teka.chamaa_finance.widgets.LoadingScreen
import org.jetbrains.compose.resources.painterResource
import kotlin.collections.forEach


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {
    val demoUser = UserData(
        userID = 1L,
        password = "demoPass123",
        user_name = "Demo User",
        mobile = "1234567890"
    )

    val userInfo = demoUser
    val isPreparingModules = false
    val snackbarHostState = remember { SnackbarHostState() }


    val moduleCards = listOf(
        HomeInfoCardData(
            title = "Members",
            value = "0",
            iconRes = Res.drawable.customers,
            color = PrimaryGreen,
            onClick = {
//                navController.navigate(route = AppScreens.FruitListScreen.route)
            }
        ),
        HomeInfoCardData(
            title = "Records",
            value = "0",
            iconRes = Res.drawable.stock_mngnt,
            color = PrimaryGreen,
            onClick = {
//                navController.navigate(route = AppScreens.CustomerListScreen.route)
            }
        ),
        HomeInfoCardData(
            title = "Analytics",
            value = "0",
            iconRes = Res.drawable.stock,
            color = PrimaryGreen,
            onClick = {
//                navController.navigate(route = AppScreens.FarmManagementModule.route)
            }
        )

    )



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
                    Text(
                        text = "Chamaa",
                        fontWeight = FontWeight.Bold,
                        fontSize = TextSizeXLarge,
                        modifier = Modifier.padding(0.dp),
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                                  },
                        content = {
                            Icon(
                                painter = painterResource(Res.drawable.chamaa_logo_no_bg),
                                contentDescription = "Settings"
                            )
                        }
                    )
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Log Out"
                            )
                        }
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = padding.calculateBottomPadding()
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = padding.calculateTopPadding(), start = 6.dp, end = 6.dp)
            ) {
                FeaturedBox(userInfo)
                LazyColumn(
                    content = {
                        items(
                            moduleCards.chunked(2)
                        ) { rowItems ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 5.dp,
                                        top = 8.dp,
                                        bottom = 8.dp
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowItems.forEach { cardData ->
                                    HomeInfoCard(
                                        title = cardData.title,
                                        value = cardData.value,
                                        iconRes = cardData.iconRes,
                                        color = cardData.color,
                                        modifier = Modifier.weight(1f),
                                        onClick = cardData.onClick
                                    )
                                }
                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                )

                if (isPreparingModules) {
                    LoadingScreen()
                }
            }


            Text(
                text = "\"Many hands make light work.\"",
                fontSize = TextSizeLarge,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraLight,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color(0xFFF7F7F7),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)

            )

//            Text(
//                fontSize = TextSizeLarge,
//                fontFamily = FontFamily.Cursive,
//                fontWeight = FontWeight.ExtraLight,
//                fontStyle = FontStyle.Italic,
//                textAlign = TextAlign.Center,
//                color = Color.Gray,
//                modifier = Modifier
//                    .fillMaxWidth()
////                    .padding(horizontal = 16.dp, vertical = 8.dp)
////                    .background(
////                        color = Color(0xFFF7F7F7),
////                        shape = RoundedCornerShape(8.dp)
////                    )
////                    .padding(16.dp)
//            )
        }
    }

}