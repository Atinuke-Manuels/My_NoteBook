package com.atinuke.my_notebook.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atinuke.my_notebook.models.NoteModel

object DatabaseConfig {
    @Volatile
    private var INSTANCE: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "note_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

@Database(entities = [NoteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}


// CODE AS WRITTEN BY MR ANTHONY
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.atinuke.my_notebook.models.NoteModel

//object DatabaseConfig {
//    fun getInstance(context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "note_db"
//        ).build()
//    }
//}
//
//@Database(entities = [NoteModel::class], version = 1)
//abstract class AppDatabase : RoomDatabase(){
//    abstract fun noteDao(): NoteDao
//}
