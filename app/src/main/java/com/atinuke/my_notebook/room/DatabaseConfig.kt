package com.atinuke.my_notebook.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atinuke.my_notebook.models.NoteModel

object DatabaseConfig {
    fun getInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "note_db"
        ).build()
    }
}

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}