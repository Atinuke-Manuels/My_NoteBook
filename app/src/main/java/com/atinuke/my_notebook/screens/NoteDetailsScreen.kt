package com.atinuke.my_notebook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.view_model.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(navController: NavController, noteId: String) {
    val noteViewModel: NoteViewModel = viewModel()
    val notes by noteViewModel.getNote(noteId).observeAsState()
    var title by rememberSaveable { mutableStateOf(notes?.title ?: "No note to retrieve") }
    var newNote by rememberSaveable { mutableStateOf(notes?.newNote ?: "No note to retrieve") }
//    var editTag by rememberSaveable  { mutableStateOf(notes?.editTag ?: "") }

    LaunchedEffect(notes) {
        title = notes?.title ?: "No note to retrieve"
        newNote = notes?.newNote ?: "No note to retrieve"
//        editTag = notes?.editTag ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Note") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        // Navigate back when the back arrow is clicked
                        navController.navigate(Routes.noteListRoute)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow")
                    }
                },
                actions = {
                    // Save changes when the "Add Note" icon is clicked
                    IconButton(onClick = {
                        // Call updateNote with the appropriate parameters
                        val updatedNote: NoteModel= notes!!.copy(
                            title=title,
                            newNote = newNote,
//                            editTag = "edited"
                        )
                        noteViewModel.updateNote(updatedNote)

                        // Navigate to the homescreen
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Save Changes")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { titleInput -> title = titleInput },
                    label = { Text(text = "Enter Title", fontWeight = FontWeight.Black) },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(80.dp),
                    maxLines = 1, // Adjust as needed
                )
                Spacer(modifier = Modifier.height(0.dp))
                TextField(
                    value = newNote,
                    onValueChange = { newNoteInput -> newNote = newNoteInput },
                    label = { Text(text = "Enter note here") },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 5,
                )
            }
        }
    )
}

