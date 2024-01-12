package com.atinuke.my_notebook.view_model

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.room.DatabaseConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class NoteViewModel(val applicationn: Application) : AndroidViewModel(applicationn) {
    private val db = DatabaseConfig.getInstance(applicationn)

//    private val _filteredNotes = MediatorLiveData<List<NoteModel>>()
//    var filteredNotes: LiveData<List<NoteModel>> = _filteredNotes

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

    fun saveNote(title: String, newNote: String) {
        val currentTimeMillis = System.currentTimeMillis()
        val note = NoteModel(
            title = title,
            newNote = newNote,
            noteTime = System.currentTimeMillis(),
            noteDate = getStartOfDay(currentTimeMillis)
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

    fun getNoteById(noteId: Int): LiveData<NoteModel?> {
        return db.noteDao().fetchNoteById(noteId)
    }

//    fun searchNotes(query: String) {
//        if (query.isNotEmpty()) {
//            // Update the source LiveData to trigger the MediatorLiveData
//            _filteredNotes.removeSource(db.noteDao().searchNotes("%"))
//            _filteredNotes.addSource(db.noteDao().searchNotes("%$query%"), _filteredNotes::setValue)
//        } else {
//            // If the query is empty, set the value to an empty list
//            _filteredNotes.value = emptyList()
//        }
//    }

//    fun getFilteredNotes(): LiveData<List<NoteModel>> {
//        return filteredNotes
//    }
}