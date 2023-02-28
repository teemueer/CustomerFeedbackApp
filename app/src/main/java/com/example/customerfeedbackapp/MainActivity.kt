package com.example.customerfeedbackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
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
import com.example.customerfeedbackapp.screens.camera.CameraView
import com.example.customerfeedbackapp.screens.customer.*
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
                val permissionViewModel: PermissionViewModel by viewModels<PermissionViewModel>()
                val productViewModel:ProductViewModel by viewModels<ProductViewModel>()
                productViewModel.getProducts_v2()
                CustomerFeedbackApp(viewModel, permissionViewModel, productViewModel)
            }
        }
    }
}

@Composable
fun CustomerFeedbackApp(viewModel: MainViewModel, permissionViewModel: PermissionViewModel, productViewModel: ProductViewModel) {
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
                    HomeScreen(/*navController, viewModel*/)
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
                    ProductsView(navController, productViewModel)
                }
                composable(route="SingleProduct"){
                    SingleProduct(productViewModel, navController)
                }
                composable(route="FeedbackView"){
                    FeedbackView(productViewModel, navController)
                }
                composable(route="FeedbackFormView"){
                    FeedbackFormView(productViewModel)
                }
                composable(route="CameraView"){
                    CameraView(productViewModel, navController)
                }
            }
        }
    }
}