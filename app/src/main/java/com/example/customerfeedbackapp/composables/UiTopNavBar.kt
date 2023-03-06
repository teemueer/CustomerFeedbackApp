package com.example.customerfeedbackapp.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User


@Composable
fun UiTopAppBar(
    navController: NavController,
    viewModel: MainViewModel,
){
    val user: User? by viewModel.currentUser.observeAsState(null)
    androidx.compose.material.TopAppBar(backgroundColor = MaterialTheme.colorScheme.primary) {
        IconButton(onClick = { navController.navigate("settings") }, ) {
            Icon(Icons.Filled.Settings, "Settings")
        }
        Text(user?.email ?: "anonymous")
    }
}