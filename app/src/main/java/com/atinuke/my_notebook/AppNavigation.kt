package com.atinuke.my_notebook

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atinuke.my_notebook.screens.AddNoteScreen
import com.atinuke.my_notebook.screens.NoteListScreen

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
    }
}

object Routes {
    var noteListRoute = "note-list"
    var addNoteRoute = "add-note"
}