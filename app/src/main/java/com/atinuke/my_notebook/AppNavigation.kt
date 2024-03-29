package com.atinuke.my_notebook

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.atinuke.my_notebook.models.NoteModel
import com.atinuke.my_notebook.screens.AddNoteScreen
import com.atinuke.my_notebook.screens.ForgotPassword
import com.atinuke.my_notebook.screens.LoginScreen
import com.atinuke.my_notebook.screens.NoteDetailsScreen
import com.atinuke.my_notebook.screens.NoteListScreen
import com.atinuke.my_notebook.screens.SignUpScreen
import com.atinuke.my_notebook.view_model.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(authViewModel: AuthViewModel){
    val navController = rememberNavController()
    var isUserSignIn by rememberSaveable { authViewModel.isUserAuthenticated}
    var isUserRegistered by rememberSaveable { authViewModel.isUserCreatedNow}
    var isPasswordResetSuccessful by rememberSaveable { authViewModel.isPasswordResetSuccessful}

    var errorMessage by rememberSaveable { authViewModel.errorMessage}
    var context = LocalContext.current


    // helps to display error messages on the UI
    if(errorMessage.isNotEmpty()) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        authViewModel.clearErrorMessage()
    }

    LaunchedEffect(authViewModel.isUserCreatedNow.value) {
        if (authViewModel.isUserCreatedNow.value) {
            navController.navigate("login")
        }
    }


    NavHost(
        navController = navController,
        // to check if a user is signed in or not
        startDestination = if(isUserRegistered){
            "login"
        } else if(isUserSignIn){
            "note-list"
        } else if(!isPasswordResetSuccessful == true){
            "login"
        }else {
            "login"
        }

    ){
        composable(Routes.loginRoute){
            LoginScreen(navController, authViewModel)
        }
        composable(Routes.signupRoute){
            SignUpScreen(navController, authViewModel)
        }
        composable(Routes.noteListRoute){
            NoteListScreen(navController, authViewModel)
        }
        composable(Routes.addNoteRoute){
            AddNoteScreen(navController, authViewModel)
        }

        composable(Routes.forgotPasswordRoute){
            ForgotPassword( authViewModel, navController,)
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

    var loginRoute = "login"
    var signupRoute= "signup"
    var forgotPasswordRoute = "forgotPassword"
}