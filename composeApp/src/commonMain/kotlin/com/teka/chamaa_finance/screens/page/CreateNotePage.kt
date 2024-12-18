package com.teka.chamaa_finance.screens.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teka.chamaa_finance.components.NoteAppBar
import com.teka.chamaa_finance.navigation.AppDestinations
import com.teka.chamaa_finance.screens.viewmodel.CreateNoteViewModel
import com.teka.chamaa_finance.util.extensions.toStringFormat
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun CreateNotePage(
    navController: NavHostController,
    isEditNote: Boolean? = false,
    noteId: Long? = null,
) {
    val viewModel = koinViewModel<CreateNoteViewModel>()

    if (viewModel.isNoteSaved.value) {
        navController.popBackStack(route = AppDestinations.Home.route, inclusive = false)
    }

    if (isEditNote == true) {
        viewModel.populateDataFromLocal(noteId)
    }

    Scaffold(
        topBar = {
            NoteAppBar(navController, viewModel, isEditNote)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.title.value,
                    textStyle = MaterialTheme.typography.titleLarge,
                    placeholder = {
                        Text(
                            text = "Title",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    onValueChange = {
                        viewModel.setTitle(it)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                Text(
                    text = viewModel.updatedAt.value.toStringFormat(),
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .height(600.dp),
                    value = viewModel.body.value,
                    textStyle = MaterialTheme.typography.titleLarge,
                    placeholder = {
                        Text(
                            text = "Start typing...",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    onValueChange = {
                        viewModel.setBody(it)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }
        }
    }
}