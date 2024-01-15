package com.atinuke.my_notebook.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.atinuke.my_notebook.models.NoteModel

//Dao is data access object
@Dao
interface NoteDao {
    @Insert
    suspend fun saveNote(note : NoteModel)

//    query to display all notes on the screen
    @Query("Select * from notes order by id DESC")
    fun fetchNotes(): LiveData<List<NoteModel>>

//    query to delete a note by id
    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Long)

//    query to edit a note by id
    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun fetchNotes(noteId: String): LiveData<NoteModel?>


    // Query to update a note by id
    @Query("UPDATE notes SET title = :newTitle, newNote = :newNote WHERE id = :noteId")
    suspend fun updateNote(noteId: String, newTitle: String, newNote: String)

//    @Delete
//    fun deleteNote()
}