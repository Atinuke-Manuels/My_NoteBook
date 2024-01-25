package com.atinuke.my_notebook.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import com.atinuke.my_notebook.R
import com.atinuke.my_notebook.Routes
import com.atinuke.my_notebook.view_model.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.oAuthProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPassword(authViewModel: AuthViewModel, navController: NavController) {
    var emailAddress by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current


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
            Text(text = "Forgot Password",
                modifier = Modifier
                    .padding(bottom = 4.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(text = "Enter email address",
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

            Button(
                onClick = {
//                    navController.navigate(Routes.noteListRoute)

                        authViewModel.forgotPassword(
                            email = emailAddress,
                            context = context
                        )
                          },
                modifier = Modifier
                    .size(240.dp, 76.dp)
                    .padding(12.dp),
                enabled = if (emailAddress == "") false else true

            ) {
                Text(
                    text = "Reset Password",
                    color = if (emailAddress == "") Color.DarkGray else Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                ) // Set label text and color
            }

            Row(modifier = Modifier
                .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center

            )
            {
                Text(text = "Just remembered your password?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
            ClickableText(
                text = AnnotatedString("Login"),
                onClick = {
                    navController.navigate(Routes.loginRoute)
                },
                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(top = 4.dp)
                ,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp,
                    color = Color.Blue,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}