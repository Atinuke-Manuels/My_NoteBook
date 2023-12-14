package com.atinuke.my_notebook.room

import androidx.room.Dao
import androidx.room.Insert
import com.atinuke.my_notebook.models.NoteModel

//Dao is data access object
@Dao
interface NoteDao {
    @Insert
    suspend fun saveNote(note : NoteModel)
}