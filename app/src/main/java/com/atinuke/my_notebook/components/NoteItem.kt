package com.atinuke.my_notebook.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.atinuke.my_notebook.models.NoteModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteItem(notes: NoteModel, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() })

    {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Text(text = notes.title, fontWeight = FontWeight.Black)
            Text(text=notes.newNote)

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
}