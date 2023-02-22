package com.example.customerfeedbackapp.composables

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User

@Composable
fun UiFab(
    navController: NavController,
    viewModel: MainViewModel,
){
    val user: User? by viewModel.currentUser.observeAsState(null)
    if (user != null) {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add, "Add")
        }
    }
}