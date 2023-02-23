package com.example.customerfeedbackapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.customerfeedbackapp.composables.BottomBar
import com.example.customerfeedbackapp.composables.UiFab
import com.example.customerfeedbackapp.composables.UiTopAppBar
import com.example.customerfeedbackapp.permissions.PermissionViewModel
import com.example.customerfeedbackapp.screens.customer.ItemMenu
import com.example.customerfeedbackapp.screens.customer.ProductsView
import com.example.customerfeedbackapp.screens.home.HomeScreen
import com.example.customerfeedbackapp.screens.home.HomeView
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
    Scaffold(

        floatingActionButton = { UiFab(navController, viewModel)
        },
        topBar = { UiTopAppBar(navController, viewModel) },
        bottomBar = { BottomBar(navController) }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
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
                composable(route="ItemMenu"){
                    ItemMenu(navController, viewModel)
                }
                composable(route="ProductsView"){
                    ProductsView(navController)
                }
            }
        }
    }
}