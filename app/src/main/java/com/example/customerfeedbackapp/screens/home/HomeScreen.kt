package com.example.customerfeedbackapp.screens.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.User
import com.example.customerfeedbackapp.permissions.PermissionViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel,
    permissionViewModel: PermissionViewModel
) {
    val user: User? by viewModel.currentUser.observeAsState(null)
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionViewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
        }
    )

    Scaffold(

        floatingActionButton = {
            if (user != null) {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Add, "Add")
                }
            }
        },

        topBar = {
            TopAppBar {
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(Icons.Filled.Settings, "Settings")
                }
                Text(user?.email ?: "anonymous")
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            /*
            Testing out permissions button.
            Button(onClick = {
                locationPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }) {
                Text(text = "This is a test")
            }
             */
            MapView()
            StoreInformation()
            StoreNews()

        }
    }
}

@Composable
fun MapView() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
        .background(Color.Black),

    ) {
    }
}

@Composable
fun StoreInformation() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
        .background(Color.Black),

        ) {
    }
}

@Composable
fun StoreNews() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
        .background(Color.Black),

        ) {
    }
}