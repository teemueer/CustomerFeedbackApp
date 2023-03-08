package com.example.customerfeedbackapp.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.models.User

@OptIn(ExperimentalMaterial3Api::class)
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

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.login), fontSize = 24.sp, fontFamily = ptserif_bold, fontWeight = FontWeight.Bold)

        TextField(
            value = email, onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email), fontFamily = ptserif_bold) },
            modifier =
            Modifier
                .height(60.dp)
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
        )



        TextField(
            value = password, onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password), fontFamily = ptserif_bold) },
            modifier =
            Modifier
                .height(60.dp)
                .background(Color.White)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
        )

        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                viewModel.login(email, password)
            },
            shape = RoundedCornerShape(3.dp)
        ) {
            Text(stringResource(R.string.login), fontFamily = ptserif_bold)
        }
    }
}