package com.atinuke.my_notebook.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import com.atinuke.my_notebook.components.NoteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(){
    var title by remember { mutableStateOf("")}
    var newNote by remember { mutableStateOf("")}

   Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Add New Note") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Add ,contentDescription = "Add Note " )
                    }
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "More Options" )
                    }
                }
            )
        },
       content = { paddingValues ->
           Column(modifier = androidx.compose.ui.Modifier.padding(paddingValues)) {
               TextField(
                   value = title,
                   onValueChange = {titleInput -> title= titleInput},
                   label = { Text(text = "Enter Title", fontWeight = FontWeight.Black)},
                   modifier = Modifier
                       .padding(horizontal = 10.dp)
                       .padding(top = 10.dp)
                       .fillMaxWidth()
                       .height(50.dp),
                   maxLines = 1, // Adjust as needed
               )
               Spacer(modifier = Modifier.height(0.dp))
               TextField(
                   value = newNote,
                   onValueChange = {newNoteInput -> newNote = newNoteInput},
                   label = { Text(text = "Enter note here")},
                   modifier = Modifier
                       .padding(horizontal = 10.dp)
                       .fillMaxWidth()
                       .height(120.dp),
                   maxLines = 5, // Adjust as needed
               )
           }
       }
   )
}



