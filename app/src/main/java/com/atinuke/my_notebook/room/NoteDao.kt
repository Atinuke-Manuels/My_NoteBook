package com.atinuke.my_notebook.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.atinuke.my_notebook.models.NoteModel

//Dao is data access object
@Dao
interface NoteDao {
    @Insert
    suspend fun saveNote(note : NoteModel)

    @Query("Select * from notes ")
    fun fetchNotes(): LiveData<List<NoteModel>>

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Long)
}