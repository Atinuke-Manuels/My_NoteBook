package com.atinuke.my_notebook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.components.NoteItem
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.view_model.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(navController: NavController) {
    var myNoteViewModel: NoteViewModel = viewModel()
    var selectedNote by remember { mutableStateOf<NoteModel?>(null) }

//    val noteLiveData = myNoteViewModel.getNoteById(noteId)
//    val note by noteLiveData.observeAsState()
//
//    // Use LaunchedEffect to update the selectedNote when the note changes
//    LaunchedEffect(note) {
//        selectedNote = note
//    }

        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(text = "Edit Note") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Edit,contentDescription = "Edit for Note " )
                        }
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note" )
                        }
                    }
                )
            },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    selectedNote?.let {
                        Text(text = it.noteDate.toString())
                        Text(text = it.title)
                        Text(text = it.newNote)
                        Text(text = it.noteTime.toString()) // Modify this to display the formatted date and time
                    }
                }
            }
        )

    }

