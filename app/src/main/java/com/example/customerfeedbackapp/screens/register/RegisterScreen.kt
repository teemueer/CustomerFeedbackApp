package com.example.customerfeedbackapp.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel

@Composable
fun RegisterScreen(mainViewModel: MainViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isOwner by remember { mutableStateOf(false) }

    Column {
        Text("Register", fontSize = 24.sp)
        TextField(value = email, onValueChange = { email = it }, label = { Text("E-Mail" ) })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Password" ) })
        Checkbox(checked = isOwner, onCheckedChange = { isOwner = it} )
        Button(onClick = { mainViewModel.register(email, password, isOwner) }) {
            Text("Register")
        }
    }
}