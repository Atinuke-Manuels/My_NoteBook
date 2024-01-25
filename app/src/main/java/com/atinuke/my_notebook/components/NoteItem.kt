package com.atinuke.my_notebook.components

import android.content.res.Resources
import android.graphics.drawable.shapes.OvalShape
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.atinuke.my_notebook.R
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.models.NoteModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteItem(notes: NoteModel,
             selectedNote: NoteModel?,
             onEditClick: () -> Unit,
             onDeleteClick: () -> Unit,
             navController: NavController){

    var showDialog by remember { mutableStateOf(false) }
    val datestamp = Instant.ofEpochMilli(notes.noteDate)
    val noteDate = LocalDateTime.ofInstant(datestamp, ZoneId.systemDefault())
    val formattedDate = noteDate.format(DateTimeFormatter.ofPattern("EEEE MMM. d, yyyy"))

    val timestamp = Instant.ofEpochMilli(notes.noteTime)
    val noteTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault())

    val formattedTime = noteTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

    val editedText = if (notes.isEdited) "Edited" else ""
    // to display date and time together
//            val formattedTime = noteTime.format(DateTimeFormatter.ofPattern("EEEE MMM. d, yyyy HH:mm:ss"));

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                showDialog = true
            }
    )
    {

        Box (
            modifier = Modifier
                .fillMaxSize()
                // To give the cards alternate colors
                .background(
                    color = if (notes.id.toInt() % 2 == 0) Color(0xFF03DAC5)else Color(0xFFFF9800),
                    shape = RectangleShape
                )// Set background color

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically, // Specify the vertical alignment here
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formattedDate,
                        color = Color.Blue,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = formattedTime,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier

                    )
                }
                Text(text = notes.title, fontWeight = FontWeight.Black)
                Text(text = notes.newNote)

                Text(
                    text = editedText,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    fontSize = 8.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 8.dp)
                )
            }
        }

    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = { Text(text = "Choose an Option",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold
                ) },
            text = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = {
                            onEditClick()
                            showDialog = false
                        },
                        modifier = Modifier
                            .background(Color.Green, shape = MaterialTheme.shapes.small)
//                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Edit", color = Color.White, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold)
                    }
                    TextButton(
                        onClick = {
                            onDeleteClick()
                            showDialog = false
                        },
                        modifier = Modifier
                            .background(Color.Red, shape = MaterialTheme.shapes.small)
//                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Delete", color = Color.White, fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold)
                    }
                }
            },
            confirmButton = {},
            dismissButton = {}
        )
    }
}


