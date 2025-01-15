package com.teka.chamaa_finance.screens.censor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.chama_base_logo_nobg
import chamaafinance.composeapp.generated.resources.group
import com.teka.chamaa_finance.navigation.AppDestinations
import com.teka.chamaa_finance.widgets.CustomTopAppBar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CensorScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<CensorViewModel>()

    var uncensoredText by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()



    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.chama_base_logo_nobg),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Chamaa",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleLarge
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
                }
            )
        }
    ) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            TextField(
                value = uncensoredText,
                onValueChange = { uncensoredText = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text("Uncensored text")
                }
            )
            Button(onClick = {
                scope.launch {
                    viewModel.isLoading.value = true
                    viewModel.errorMessage.value = null
                    viewModel.censorText(uncensoredText)
                }
            }) {
                if(viewModel.isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(15.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                } else {
                    Text("Censor!")
                }
            }

            viewModel.censoredText.value?.let {
                Text(it)
            }
            viewModel.errorMessage.value?.let {
                Text(
                    text = it.name,
                    color = Color.Red
                )
            }
        }


    }


}