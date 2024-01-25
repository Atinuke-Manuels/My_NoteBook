package com.atinuke.my_notebook.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthViewModel: ViewModel() {
    var isUserAuthenticated = mutableStateOf(false)
    var isUserRegistered = mutableStateOf( false)
    var errorMessage = mutableStateOf("")

    fun loginUser(email: String, password: String){
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    isUserAuthenticated.value = true
                }else{
                    errorMessage.value = task.exception?.message.toString()
                    Log.e("SIGN In Not Successful", task.exception.toString())
                }
            }

    }

    fun signinUserWithGoogle(){
    }

    fun registerUser(username: String, email: String, password: String, confirmpassword : String, phonenum: String){
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {task->
                if(task.isSuccessful){
                    isUserAuthenticated.value = true

                }else{
                    errorMessage.value = task.exception?.message.toString()
                    Log.e("SIGN In Not Successful", task.exception.toString())
                }
            }
    }
}