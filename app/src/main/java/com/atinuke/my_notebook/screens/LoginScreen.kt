package com.atinuke.my_notebook.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.atinuke.my_notebook.R
import com.atinuke.my_notebook.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var userName by rememberSaveable { mutableStateOf("") }
    var passwordInputText by rememberSaveable { mutableStateOf("") }

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
//            Text(
//                text = "Login",
//                modifier = Modifier
//                    .align(Alignment.CenterHorizontally)
//                    .padding(bottom = 44.dp),
//                style = MaterialTheme.typography.titleMedium.copy(
//                    color = Color.Blue, // Change the color as needed
//                    fontSize = 30.sp // Change the font size as needed
//                )
//            )
            Image(
                painter = painterResource(id = R.drawable.notelogo),
                contentDescription = "Note App background",
                modifier = Modifier
                    .size(320.dp) // Adjust the size as needed



            )

            OutlinedTextField(
                value = userName,
                onValueChange = { titleInput -> userName = titleInput },
                placeholder = { Text(text = "Enter UserName", fontWeight = FontWeight.Light) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
            )
            OutlinedTextField(
                value = passwordInputText,
                onValueChange = { titleInput -> passwordInputText = titleInput },
                placeholder = { Text(text = "Enter Password", fontWeight = FontWeight.Light) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, MaterialTheme.shapes.small),
            )
            ClickableText(
                text = AnnotatedString("Forgot Password"),
                onClick = {
                    navController.navigate(Routes.noteListRoute)
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp, start = 8.dp, bottom = 24.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    color = Color.Blue
                )
            )
            Button(
                onClick = {
                    navController.navigate(Routes.noteListRoute)
                },
                modifier = Modifier
                    .size(140.dp, 60.dp)
                    .padding(8.dp),
                enabled = if (userName == "" || passwordInputText == "") false else true

            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 16.sp
                ) // Set label text and color
            }
        }
    }
    }
