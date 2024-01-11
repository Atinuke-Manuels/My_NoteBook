package com.atinuke.my_notebook.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.models.NoteModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteItem(notes: NoteModel,
//             onClick: () -> Unit,
             selectedNote: NoteModel?,
             onEditClick: () -> Unit,
             onDeleteClick: () -> Unit,
             navController: NavController){

    var isDropdownExpanded by rememberSaveable { mutableStateOf(false) }

    // Function to handle dropdown item click
    val onDropdownItemClick: (String) -> Unit = { action ->
        when (action) {
            "Edit" -> onEditClick()
            "Delete" -> onDeleteClick()
        }
        isDropdownExpanded = false
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
//            .clickable { onClick() }
            .clickable {
                // Toggle the dropdown menu
                isDropdownExpanded = !isDropdownExpanded

                // If the dropdown menu is not expanded, navigate to noteDetailsRoute
                if (!isDropdownExpanded) {
                    navController.navigate(Routes.noteDetailsRoute)
                }
            }
    )

    {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val datestamp = Instant.ofEpochMilli(notes.noteDate)
            val noteDate = LocalDateTime.ofInstant(datestamp, ZoneId.systemDefault())
            val formattedDate = noteDate.format(DateTimeFormatter.ofPattern("EEEE MMM. d, yyyy"))


            Text(
                text = formattedDate,
                color = Color.Blue,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 4.dp)
            )
            Text(text = notes.title, fontWeight = FontWeight.Black)
            Text(text = notes.newNote)

//            val currentTime = LocalDateTime.now()
//            val formattedTime = currentTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

            val timestamp = Instant.ofEpochMilli(notes.noteTime)
            val noteTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault())

            val formattedTime = noteTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

            Text(
                text = formattedTime,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }
    }
    DropdownMenu(expanded = isDropdownExpanded,
        onDismissRequest = { /*TODO*/ },
        modifier = Modifier
            .padding(8.dp)) {
    DropdownMenuItem(
        text = {Text(text ="Edit")},
        onClick  = { onEditClick() })
        DropdownMenuItem(
            text = {Text(text ="Delete")},
            onClick  = { onDeleteClick() })
    }
}


