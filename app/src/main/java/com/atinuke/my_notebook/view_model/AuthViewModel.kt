package com.atinuke.my_notebook.view_model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthViewModel: ViewModel() {
    var isUserAuthenticated = mutableStateOf(false)
    var isUserCreatedNow = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var isPasswordResetSuccessful = mutableStateOf( false)

    // Callback to notify the UI layer about events, like successful registration
    private var onUserRegistered: (() -> Unit)? = null


    fun loginUser(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isUserAuthenticated.value = true
                } else {
                    errorMessage.value = task.exception?.message.toString()
                    Log.e("SIGN In Not Successful", task.exception.toString())
                }
            }

    }

    fun signinUserWithGoogle() {
    }


    fun registerUser(username: String, email: String, password: String, confirmpassword: String, phonenum: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isUserCreatedNow.value = true
                    // Notify the UI layer about successful registration
                    onUserRegistered?.invoke()

                } else {
                    errorMessage.value = task.exception?.message.toString()
                    Log.e("User Creation Not Successful", task.exception.toString())
                }
            }
    }


    fun clearErrorMessage() {
        errorMessage.value = ""
    }

    fun forgotPassword(email: String, context: Context) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Reset mail sent to $email", Toast.LENGTH_LONG).show()
                    isPasswordResetSuccessful.value = true
                } else {
                    Toast.makeText(context, "Failed! Try Again or Create An Account", Toast.LENGTH_LONG).show()
                }
            }
    }



    fun logout(){
        Firebase.auth.signOut()
        isUserAuthenticated.value = false
    }
}