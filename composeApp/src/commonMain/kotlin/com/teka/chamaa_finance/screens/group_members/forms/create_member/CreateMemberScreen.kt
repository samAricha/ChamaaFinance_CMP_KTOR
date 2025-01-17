package com.teka.chamaa_finance.screens.group_members.forms.create_member

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import chamaafinance.composeapp.generated.resources.Res
import chamaafinance.composeapp.generated.resources.group
import com.teka.chamaa_finance.navigation.AppDestinations
import com.teka.chamaa_finance.ui.theme.appShapes
import com.teka.chamaa_finance.util.formattedTimeBasedOnTimeFormat
import com.teka.chamaa_finance.util.today
import com.teka.chamaa_finance.widgets.CustomButton
import com.teka.chamaa_finance.widgets.CustomDateBoxField
import com.teka.chamaa_finance.widgets.CustomDatePicker
import com.teka.chamaa_finance.widgets.CustomInputTextField
import com.teka.chamaa_finance.widgets.CustomTimeBoxField
import com.teka.chamaa_finance.widgets.CustomTimePickerDialog
import com.teka.chamaa_finance.widgets.CustomTopAppBar
import com.teka.chamaa_finance.widgets.LoadingScreen
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun CreateMemberScreen(
    navController: NavHostController
) {
    val viewModel = koinViewModel<CreateMemberViewModel>()
    val uiState = viewModel.createMemberUiState.collectAsState().value
    val showDatePickerDialog = uiState.showDatePicker

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Clock.System.now().toEpochMilliseconds(),
    )
    if (showDatePickerDialog) {
        CustomDatePicker(
            datePickerState = datePickerState,
            onDismiss = {
                viewModel.updateModelField(CreateMemberUiState::showDatePicker, false)
            },
            onConfirmDate = {it ->
                viewModel.updateModelField(CreateMemberUiState::date, it)
                viewModel.updateModelField(CreateMemberUiState::showDatePicker, false)
            },
        )
    }

    val startTimeState = rememberTimePickerState(
        initialHour = today().hour,
        initialMinute = today().minute,
        is24Hour = false,
    )
    if (uiState.showTimePicker) {
        CustomTimePickerDialog(
            title = "Time",
            state = startTimeState,
            onDismiss = {
                viewModel.updateModelField(CreateMemberUiState::showTimePicker, false)
            },
            onConfirmStartTime = {time ->
                viewModel.updateModelField(CreateMemberUiState::time, time)
                viewModel.updateModelField(CreateMemberUiState::showTimePicker, false)
            },
        )
    }



    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add New Member",
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
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CustomDateBoxField(
                            modifier = Modifier.weight(1f),
                            currentTextState = uiState.date.date.toString(),
                            onClick = {
                                viewModel.updateModelField(
                                    CreateMemberUiState::showDatePicker,
                                    true
                                )
                            },
                            textStyle = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 16.sp,
                            ),
                            shape = appShapes.small
                        )
                        CustomTimeBoxField(
                            modifier = Modifier.weight(1f),
                            currentTextState = uiState.time.formattedTimeBasedOnTimeFormat(12),
                            onClick = {
                                viewModel.updateModelField(
                                    CreateMemberUiState::showTimePicker,
                                    true
                                )
                            },
                            textStyle = MaterialTheme.typography.titleSmall.copy(
                                fontSize = 16.sp,
                            ),
                            shape = appShapes.medium
                        )
                    }
                }

                item {
                    CustomInputTextField(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        labelText = "First Name",
                        value = uiState.firstName,
                        onValueChange = {
                            viewModel.updateStringField(CreateMemberUiState::firstName, it)
                        },
                        placeholderText = "Enter First Name ...",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp,
                        ),
                    )
                }

                item {
                    CustomInputTextField(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        labelText = "Last Name",
                        value = uiState.lastName,
                        onValueChange = {
                            viewModel.updateStringField(CreateMemberUiState::lastName, it)
                        },
                        placeholderText = "Enter Last Name ...",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp,
                        ),
                    )
                }

                item {
                    CustomInputTextField(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        labelText = "Phone Number",
                        value = uiState.phoneNumber,
                        onValueChange = {
                            viewModel.updateStringField(CreateMemberUiState::phoneNumber, it)
                        },
                        placeholderText = "0711 ...",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp,
                        ),
                    )
                }

                item {
                    CustomInputTextField(
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        labelText = "Email",
                        value = uiState.email,
                        onValueChange = {
                            viewModel.updateStringField(CreateMemberUiState::email, it)
                        },
                        placeholderText = "enter email address ...",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                        ),
                        textStyle = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 16.sp,
                        ),
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    CustomButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            viewModel.saveMember()
                        },
                        btnText = "Save Member",
                    )
                }

            }

            if (uiState.isSavingFormData) {
                LoadingScreen()
            }
        }

    }

}