package com.atinuke.my_notebook.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    var emailAddress by rememberSaveable { mutableStateOf("") }
    var passwordInputText by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
//    val authViewModel : AuthViewModel = viewModel()

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
            Text(text = "Welcome back!!!",
                modifier = Modifier
                    .padding(bottom = 4.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(text = "Enter username & password",
                modifier = Modifier,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.notelogo),
                contentDescription = "Note App background",
                modifier = Modifier
                    .size(240.dp) // Adjust the size as needed



            )

            OutlinedTextField(
                value = emailAddress,
                onValueChange = { titleInput -> emailAddress = titleInput },
                placeholder = { Text(text = "Email", fontWeight = FontWeight.Light, color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .height(56.dp)
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small),
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null, tint = Color.Gray.copy(alpha = 0.7f)) },
            )
            OutlinedTextField(
                value = passwordInputText,
                onValueChange = { titleInput -> passwordInputText = titleInput },
                placeholder = { Text(text = "Enter Password", fontWeight = FontWeight.Light, color = Color.Gray) },
                visualTransformation = if (passwordVisibility) {
                    VisualTransformation.None // Show plain text
                } else {
                    PasswordVisualTransformation() // Mask the password
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small),
                leadingIcon = { Icon(Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Gray.copy(alpha = 0.7f))},
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
            ClickableText(
                text = AnnotatedString("Forgot Password"),
                onClick = {
                    navController.navigate(Routes.noteListRoute)
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp, end = 8.dp, bottom = 16.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    color = Color.Blue,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            )
            Button(
                onClick = {
//                    navController.navigate(Routes.noteListRoute)
                    authViewModel.loginUser(
                        email = emailAddress,
                        password = passwordInputText,
                    )},
                modifier = Modifier
                    .size(140.dp, 76.dp)
                    .padding(12.dp),
                enabled = if (emailAddress == "" || passwordInputText == "") false else true

            ) {
                Text(
                    text = "Login",
                    color = if (emailAddress == "" || passwordInputText == "") Color.DarkGray else Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                ) // Set label text and color
            }

            Text(text = "---- or ----",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 8.dp, bottom = 4.dp)
            )
            Row(modifier = Modifier
                .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center

            )
            {
                Image(painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Icon" ,
                    modifier = Modifier
                        .size(32.dp)
                )
                Text(text = "Login with Google.?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
            ClickableText(
                text = AnnotatedString("Create an account"),
                onClick = {
                    navController.navigate(Routes.signupRoute)
                },
                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(top = 4.dp)
                ,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    color = Color.DarkGray,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
    }
