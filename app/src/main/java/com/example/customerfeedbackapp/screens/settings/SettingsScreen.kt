package com.example.customerfeedbackapp.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User

@Composable
fun SettingsScreen(navController: NavController, mainViewModel: MainViewModel) {
    val user: User? by mainViewModel.currentUser.observeAsState(null)

    Column() {
        Row(modifier = Modifier.clickable { navController.navigate("login") }) {
            if (user == null) {
                Icon(Icons.Filled.AccountBox, "Sign in")
                Text("Sign in", modifier = Modifier.clickable { navController.navigate("login") })
            } else {
                Icon(Icons.Filled.AccountBox, "Log out")
                Text("Log out", modifier = Modifier.clickable {
                    mainViewModel.logout()
                    navController.navigate("home")
                })
            }
        }
    }
}