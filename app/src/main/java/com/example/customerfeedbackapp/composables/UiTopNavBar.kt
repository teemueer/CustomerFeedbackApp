package com.example.customerfeedbackapp.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User


@Composable
fun UiTopAppBar(
    navController: NavController,
    viewModel: MainViewModel,
){
    val user: User? by viewModel.currentUser.observeAsState(null)
    androidx.compose.material.TopAppBar {
        IconButton(onClick = { navController.navigate("settings") }) {
            Icon(Icons.Filled.Settings, "Settings")
        }
        Text(user?.email ?: "anonymous")
    }
}