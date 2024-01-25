package com.atinuke.my_notebook.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.atinuke.my_notebook.R
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.view_model.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel) {
//    val authViewModel : AuthViewModel = viewModel()
    var phoneNum by rememberSaveable { mutableStateOf("") }
    var userName by rememberSaveable { mutableStateOf("") }
    var emailAddress by rememberSaveable { mutableStateOf("") }
    var passwordInputText by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var passwordVisibilityUp by remember { mutableStateOf(false) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isPasswordMatchValid by remember { mutableStateOf(true) }
    var isUserNameValid by remember { mutableStateOf(true)}


    Box (
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Image(painter = painterResource(id = R.drawable.noteappbg),
            contentDescription = "Note App background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Create An Account",
                modifier = Modifier,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.notelogo),
                contentDescription = "Note App background",
                modifier = Modifier
                    .size(140.dp) // Adjust the size as needed

            )
            OutlinedTextField(
                value = userName,
                onValueChange = {
                    // Check if password is within the desired length
                    isUserNameValid = it.length >= 5
                    userName = it
                },
                placeholder = { Text(text = "UserName", fontWeight = FontWeight.Light, color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(1.dp,
                        if (!isUserNameValid && userName.isNotEmpty()) Color.Red else Color.Gray,
                        MaterialTheme.shapes.small
                        ),
                leadingIcon = { Icon(Icons.Filled.PersonPin, contentDescription = null, tint = Color.Gray.copy(alpha = 0.7f)) },
            )
            if (!isUserNameValid && userName.isNotEmpty()) {
                Text(
                    text = "User name should be at least 5 characters",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 12.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            OutlinedTextField(
                value = phoneNum,
                onValueChange = { titleInput -> phoneNum = titleInput },
                placeholder = { Text(text = "+234 7000000", fontWeight = FontWeight.Light, color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                    .padding(0.dp),
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Gray.copy(alpha = 0.7f)) },
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))

            OutlinedTextField(
                value = emailAddress,
                onValueChange = {
                    emailAddress = it
                    // Check if email is in the desired format
                    isEmailValid = it.contains("@")
                },
                isError = !isEmailValid,
                placeholder = { Text(text = "email@xyz.com", fontWeight = FontWeight.Light, color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        1.dp,
                        if (!isEmailValid && emailAddress.isNotEmpty()) Color.Red else Color.Gray,
                        MaterialTheme.shapes.small
                    ),
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null, tint = Color.Gray.copy(alpha = 0.7f)) },
            )
            if (!isEmailValid && emailAddress.isNotEmpty()) {
                Text(
                    text = "Invalid email format",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 12.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))
            OutlinedTextField(
                value = passwordInputText,
                onValueChange = {
                    // Check if password is within the desired length
                    isPasswordValid = it.length >= 8
                    passwordInputText = it
                },
                isError = !isPasswordValid,
                placeholder = { Text(text = "Password", fontWeight = FontWeight.Light, color = Color.Gray) },
                visualTransformation = if (passwordVisibilityUp) {
                    VisualTransformation.None // Show plain text
                } else {
                    PasswordVisualTransformation() // Mask the password
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        1.dp,
                        if (!isPasswordValid && passwordInputText.isNotEmpty()) Color.Red else Color.Gray, // Change border color based on password validity
                        MaterialTheme.shapes.small
                    ),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray.copy(alpha = 0.7f)) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibilityUp = !passwordVisibilityUp }) {
                        Icon(
                            imageVector = if (passwordVisibilityUp) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisibilityUp) "Hide Password" else "Show Password",
                            tint = Color.Gray.copy(alpha = 0.7f)
                        )
                    }
                },
            )
            // Display an error message if the password length is not valid
            if (!isPasswordValid && passwordInputText.isNotEmpty()) {
                Text(
                    text = "Password must be at least 8 characters",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 12.dp)
                        .align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    // Check if the confirm password matches the password
                    isPasswordMatchValid = it == passwordInputText
                    confirmPassword = it
                },
                isError = !isPasswordMatchValid,
                placeholder = { Text(text = "Confirm Password", fontWeight = FontWeight.Light, color = Color.Gray) },
                visualTransformation = if (passwordVisibility) {
                    VisualTransformation.None // Show plain text
                } else {
                    PasswordVisualTransformation() // Mask the password
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        1.dp,
                        if (!isPasswordMatchValid && confirmPassword.isNotEmpty()) Color.Red else Color.Gray, // Change border color based on password match validity
                        MaterialTheme.shapes.small
                    ),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.Gray.copy(alpha = 0.7f)) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisibility) "Hide Password" else "Show Password",
                            tint = Color.Gray.copy(alpha = 0.7f)
                        )
                    }
                },
            )

            // Display an error message if the password confirmation doesn't match the password
            if (!isPasswordMatchValid && confirmPassword.isNotEmpty()) {
                Text(
                    text = "Passwords do not match",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 12.dp)
                        .align(Alignment.Start)
                )
            }

            Button(
                onClick = {
//                    navController.navigate(Routes.noteListRoute)
                          authViewModel.registerUser(
                              email = emailAddress,
                              password = passwordInputText,
                              username = userName,
                              phonenum = phoneNum,
                              confirmpassword = confirmPassword)
                },
                modifier = Modifier
                    .size(140.dp, 72.dp)
                    .padding(8.dp),
                enabled = if (phoneNum == ""|| userName == "" || emailAddress == ""|| passwordInputText == "" || confirmPassword == "" || !isEmailValid || !isPasswordValid || !isPasswordMatchValid ) false else true

            ) {
                Text(
                    text = "Sign Up",
                    color = if (phoneNum == ""|| userName == "" || emailAddress == ""|| passwordInputText == "" || confirmPassword == "" || !isEmailValid || !isPasswordValid || !isPasswordMatchValid ) Color.DarkGray else Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                ) // Set label text and color
            }
//            if (passwordInputText !== confirmPassword){
//                Snackbar(snackbarData = "password & confirm password does not match")
//            }
            Text(text = "---- or ----",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
            )
            Row(modifier = Modifier,
                horizontalArrangement = Arrangement.Center

            )
            {
                Image(painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Icon" ,
                    modifier = Modifier
                        .size(32.dp)
                )
                Text(text = "sign up with Google.?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }

            Row(modifier = Modifier,
                horizontalArrangement = Arrangement.Center

            )
            {
                Text(text = "Already Have An Account?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp
                    )
                ClickableText(
                    text = AnnotatedString("Login"),
                    onClick = {
                        navController.navigate(Routes.loginRoute)
                    },
                    modifier = Modifier
//                .align(Alignment.Start)
                        .padding( start = 8.dp, bottom = 16.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,
                        fontFamily = FontFamily.Cursive
                    )
                )
            }
        }
    }
}