package com.atinuke.my_notebook.view_model

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.room.DatabaseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
    fun saveNote(title: String, newNote: String) {
        val note = NoteModel(
            title = title,
            newNote = newNote
        )
        val db = DatabaseConfig.getInstance(applicationn)

        viewModelScope.launch(Dispatchers.IO) {
            // Perform the database operation (insert, update, etc.) using appDatabase
            db.noteDao().saveNote(note)
        }
    }
}