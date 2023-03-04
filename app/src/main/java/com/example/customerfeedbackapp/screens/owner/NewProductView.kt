package com.example.customerfeedbackapp.screens.owner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.customerfeedbackapp.screens.camera.CameraView
import com.example.customerfeedbackapp.screens.customer.ProductViewModel


@Composable
fun NewProductView(navController: NavController, productViewModel: ProductViewModel){
    var code by remember { mutableStateOf("") }
    var showCamera by remember { mutableStateOf(false) }

    if(showCamera) {
        Column() {
            Row() {
                Text(text = code)
                Button(onClick = {showCamera = !showCamera}) {
                    Text(text = "Close Camera")
                }
            }
            CameraView(code, onValueChange = { value -> code = value })
        }
    }else {
        Button(onClick = {showCamera = !showCamera}) {
            Text(text = "Open Camera")
        }
    }

}