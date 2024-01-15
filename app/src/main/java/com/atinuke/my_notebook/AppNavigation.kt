package com.atinuke.my_notebook

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.screens.AddNoteScreen
import com.atinuke.my_notebook.screens.NoteDetailsScreen
import com.atinuke.my_notebook.screens.NoteListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.noteListRoute
    ){
        composable(Routes.noteListRoute){
            NoteListScreen(navController)
        }
        composable(Routes.addNoteRoute){
            AddNoteScreen(navController)
        }

        composable("note-details/{noteId}"){
            NoteDetailsScreen(
                navController,
                noteId = it.arguments!!.getString("noteId").toString()
            )
        }
    }
}

object Routes {
    var noteListRoute = "note-list"
    var addNoteRoute = "add-note"
//    var noteDetailsRoute = "note-details"

    fun NoteDetails(noteId: String): String{
        return "note-details/$noteId"
    }
}