package com.example.customerfeedbackapp.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User

@Composable
fun LoginScreen(navController: NavController, viewModel: MainViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val user: User? by viewModel.currentUser.observeAsState(null)

    if (user != null) {
        LaunchedEffect(true) {
            navController.navigate("home")
        }
    }

    Column {
        Text("Login", fontSize = 24.sp)
        TextField(value = email, onValueChange = { email = it }, label = { Text("E-Mail" ) })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password" ) })
        Button(onClick = {
            viewModel.login(email, password)
        }) {
            Text("Login")
        }
    }
}