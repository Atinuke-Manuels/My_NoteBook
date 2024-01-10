package com.atinuke.my_notebook.view_model

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.room.DatabaseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
    private val db = DatabaseConfig.getInstance(applicationn)

    fun saveNote(title: String, newNote: String) {
        val note = NoteModel(
            title = title,
            newNote = newNote,
            noteTime = System.currentTimeMillis()
        )


        viewModelScope.launch(Dispatchers.IO) {
            // Perform the database operation (insert, update, etc.) using appDatabase
            db.noteDao().saveNote(note)
        }
    }

    fun getAllNotes(): LiveData<List<NoteModel>>{
        return db.noteDao().fetchNotes()
    }

    fun deleteNote(notes: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            db.noteDao().deleteNoteById(notes.id) // Assuming your NoteModel has an 'id' property
        }
    }
}