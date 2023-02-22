package com.example.customerfeedbackapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.customerfeedbackapp.permissions.PermissionViewModel
import com.example.customerfeedbackapp.screens.customer.ItemMenu
import com.example.customerfeedbackapp.screens.home.HomeScreen
import com.example.customerfeedbackapp.screens.login.LoginScreen
import com.example.customerfeedbackapp.screens.settings.SettingsScreen
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme
import kotlinx.coroutines.coroutineScope

class MainActivity() : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomerFeedbackAppTheme {
                val permissionViewModel: PermissionViewModel by viewModels<PermissionViewModel>()
                CustomerFeedbackApp(viewModel, permissionViewModel)
            }
        }
    }
}

@Composable
fun CustomerFeedbackApp(viewModel: MainViewModel, permissionViewModel: PermissionViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController, viewModel, permissionViewModel)
        }
        composable(route = "settings") {
            SettingsScreen(navController, viewModel)
        }
        composable(route = "login") {
            LoginScreen(navController, viewModel)
        }
        composable(route="ItemMenu"){
            ItemMenu(navController, viewModel)
        }
    }
}