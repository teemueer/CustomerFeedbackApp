package com.example.customerfeedbackapp.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.fonts.ptserif_regular
import com.example.customerfeedbackapp.models.User


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiTopAppBar(
    navController: NavController,
    viewModel: MainViewModel,
) {
    val user: User? by viewModel.currentUser.observeAsState(null)
    CenterAlignedTopAppBar(

        title = {
            if(user == null) {
                Text(
                    text = "Custo",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = ptserif_bold,
                )
            }
        }
        ,
        navigationIcon = {
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(Icons.Filled.Settings, "Settings")
            }
        },
        actions = {
            if (user != null) Text(user?.email ?: "anonymous", fontFamily = ptserif_regular)
        }
    )


}