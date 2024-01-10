package com.atinuke.my_notebook.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.atinuke.my_notebook.AppNavigation
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.components.NoteItem
import com.atinuke.my_notebook.models.NoteModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import com.atinuke.my_notebook.view_model.NoteViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(navController : NavController){
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<NoteModel?>(null) }

    val myNoteViewModel: NoteViewModel = viewModel()
    val notesFromDB by myNoteViewModel.getAllNotes().observeAsState(emptyList())

    val density = LocalDensity.current

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "My Notes") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Search ,contentDescription = "Search for Note " )
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Options" )
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                items(notesFromDB) { notes ->
                    // Replace this with your NoteItem content
                    NoteItem(
                        notes = notes,
                        onClick = {
                            // Handle regular click behavior
                            selectedNote = notes
                            showDeleteDialog = true
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController. navigate(Routes.addNoteRoute)}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note" )
            }
        }
    )
    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                selectedNote?.let {
                    myNoteViewModel.deleteNote(it)
                    selectedNote = null
                    showDeleteDialog = false
                }
            },
            onDismiss = {
                selectedNote = null
                showDeleteDialog = false
            }
        )
    }
}


@Composable
fun DeleteConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Delete Note") },
        text = { Text(text = "Are you sure you want to delete this note?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "No")
            }
        }
    )
}


//@Preview
//@Composable
//fun NoteListScreenPreview(){
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ){
//        NoteListScreen(navController = navController)
//    }
//}

@Preview
@Composable
fun NoteListScreenPreview() {
    val navController = rememberNavController()
    AppNavigation()
}