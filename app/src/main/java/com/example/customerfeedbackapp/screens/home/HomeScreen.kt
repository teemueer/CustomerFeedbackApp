package com.example.customerfeedbackapp.screens.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.customerfeedbackapp.permissions.PermissionViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel, permissionViewModel: PermissionViewModel) {
    val user: User? by viewModel.currentUser.observeAsState(null)
    val dialogQueue = permissionViewModel.visiblePermissionDialogue
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionViewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
        }
    )

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
    Column() {
        Button(onClick = { locationPermissionLauncher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )}) {
            Text(text = "This is a test")
        }
    }
}

@Composable
fun MapView(){

}

@Composable
fun StoreInformation(){

}

@Composable
fun StoreNews(){

}