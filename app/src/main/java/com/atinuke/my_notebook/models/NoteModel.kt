package com.atinuke.my_notebook.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String,
    var newNote: String,
    var noteTime: Long,
    val noteDate: Long,
    var isEdited: Boolean = false
)
