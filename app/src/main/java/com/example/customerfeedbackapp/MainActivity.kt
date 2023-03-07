package com.example.customerfeedbackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.customerfeedbackapp.screens.home.HomeScreen
import com.example.customerfeedbackapp.screens.login.LoginScreen
import com.example.customerfeedbackapp.screens.settings.SettingsScreen
import com.example.customerfeedbackapp.ui.theme.CustomerFeedbackAppTheme

class MainActivity() : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomerFeedbackAppTheme {
                CustomerFeedbackApp(viewModel)
            }
        }
    }
}

@Composable
fun CustomerFeedbackApp(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController, viewModel)
        }
        composable(route = "settings") {
            SettingsScreen(navController, viewModel)
        }
        composable(route = "login") {
            LoginScreen(navController, viewModel)
        }
    }
}