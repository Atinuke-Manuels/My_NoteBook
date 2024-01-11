package com.atinuke.my_notebook.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val newNote: String,
    val noteTime: Long,
    val noteDate: Long,
)
