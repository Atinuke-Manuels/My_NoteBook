package com.atinuke.my_notebook.view_model

import android.app.Application
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.room.DatabaseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
    private val db = DatabaseConfig.getInstance(applicationn)

    private fun getStartOfDay(currentTimeMillis: Long): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = currentTimeMillis
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.timeInMillis
    }

    fun saveNote(title: String, newNote: String,) {
        if (title.isEmpty() && newNote.isEmpty()) return  // to ensure an empty note is not added
        val currentTimeMillis = System.currentTimeMillis()
        val note = NoteModel(
            title = title,
            newNote = newNote,
            noteTime = System.currentTimeMillis(),
            noteDate = getStartOfDay(currentTimeMillis),
//            editTag = editTag
            )


        viewModelScope.launch(Dispatchers.IO) {
            // Perform the database operation (insert, update, etc.) using appDatabase
            db.noteDao().saveNote(note)
        }
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return db.noteDao().fetchNotes()
    }

    fun deleteNote(notes: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            db.noteDao().deleteNoteById(notes.id) // Assuming your NoteModel has an 'id' property
        }
    }

    fun getNote(noteId: String): LiveData<NoteModel?> {
        return db.noteDao().fetchNotes(noteId)
    }

    fun updateNote(note: NoteModel){
            viewModelScope.launch {
                db.noteDao().updateNote(note.copy(isEdited = true))
            }
    }

}

