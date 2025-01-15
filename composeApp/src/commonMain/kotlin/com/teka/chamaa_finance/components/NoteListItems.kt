package ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teka.kmp_sample.components.NoteItem
import com.teka.chamaa_finance.screens.viewmodel.HomeViewModel



@Composable
fun NoteListItems(
    viewModel: HomeViewModel,
    navController: NavHostController,
) {
    Column(
        Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        LazyColumn {
            items(viewModel.allNotes.value) { note ->
                NoteItem(note, viewModel, navController)
            }
        }
    }
}