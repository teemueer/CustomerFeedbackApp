package com.example.customerfeedbackapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    val user: User? by viewModel.currentUser.observeAsState(null)

    Scaffold(floatingActionButton = {
        if (user != null) {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    }) {
        Column() {
            TopAppBar() {
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(Icons.Filled.Settings, "Settings")
                }
                Text(user?.email ?: "anonymous")
            }
        }
    }
}