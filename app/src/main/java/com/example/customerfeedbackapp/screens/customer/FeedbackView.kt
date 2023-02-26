package com.example.customerfeedbackapp.screens.customer

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.customerfeedbackapp.permissions.PermissionViewModel

@Composable
fun FeedbackView(productViewModel: ProductViewModel,  permissionViewModel: PermissionViewModel, navController: NavController){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val viewModel = viewModel<PermissionViewModel>()

    val cameraPermissionresultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGranted ->
            viewModel.onPermissionResult(
                permission = Manifest.permission.CAMERA,
                isGranted = isGranted
            )
        }
    )

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Text(text = "Leave deedback about a product by either selecting an item from the list or")
        Text(text = "scanning the barcode using the camera on your device!")
        Button(onClick = {
            cameraPermissionresultLauncher.launch(
                Manifest.permission.CAMERA
            )
        }) {
            Text(text = "Open camera")
        }
        SearchView(state = textState )
        ProductList(navController = navController, productViewModel = productViewModel, state = textState, true)
    }
}