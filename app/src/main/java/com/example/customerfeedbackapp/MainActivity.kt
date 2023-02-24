package com.example.customerfeedbackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.screens.home.HomeScreen
import com.example.customerfeedbackapp.screens.login.LoginScreen
import com.example.customerfeedbackapp.screens.new_product.NewProductScreen
import com.example.customerfeedbackapp.screens.new_product.NewProductViewModel
import com.example.customerfeedbackapp.screens.product.ProductScreen
import com.example.customerfeedbackapp.screens.product.ProductViewModel
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
fun CustomerFeedbackApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController, mainViewModel)
        }
        composable(route = "settings") {
            SettingsScreen(navController, mainViewModel)
        }
        composable(route = "login") {
            LoginScreen(navController, mainViewModel)
        }
        composable(route = "new_product") {
            NewProductScreen(navController, mainViewModel)
        }
        composable(
            route = "products/{product_id}",
            arguments = listOf(navArgument("product_id") { type = NavType.IntType })
        ) {
            ProductScreen(navController, mainViewModel)
        }
    }
}