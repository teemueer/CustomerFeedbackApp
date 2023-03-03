package com.example.customerfeedbackapp.screens.customer

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.customerfeedbackapp.permissions.PermissionViewModel
import kotlinx.coroutines.*

@Composable
fun FeedbackView(productViewModel: ProductViewModel, navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val viewModel = viewModel<PermissionViewModel>()

    val cameraPermissionresultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = Manifest.permission.CAMERA,
                isGranted = isGranted
            )
        }
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        FeedbackInstruction()
        Spacer(modifier = Modifier.height(10.dp))
        SearchView(state = textState)
        Button(onClick = {
            CoroutineScope(Dispatchers.Main).launch {
                cameraPermissionresultLauncher.launch(
                    Manifest.permission.CAMERA
                )
                delay(3000)
            }
            navController.navigate("CameraView")
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Open camera")
        }
        Column(
            modifier = Modifier
                .heightIn(min = Dp.Unspecified, max = 550.dp)
                .padding(bottom = 10.dp)
        ) {
            ProductList(
                navController = navController,
                productViewModel = productViewModel,
                state = textState,
                true
            )

        }


    }
}

@Composable
fun FeedbackInstruction() {
    Column {
        Text(
            text = "Jätä palautetta valitsemalla tuote, tai skannaamalla tuotteen viivakoodi laitteesi kameralla",
            fontSize = 20.sp,
        )
    }
}


