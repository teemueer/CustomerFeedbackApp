package com.example.customerfeedbackapp.screens.owner

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.screens.camera.CameraView
import com.example.customerfeedbackapp.screens.customer.ProductViewModel


@Composable
fun NewProductView(navController: NavController, productViewModel: ProductViewModel) {
    var code by remember { mutableStateOf("") }
    var showCamera by remember { mutableStateOf(false) }
    val context = LocalContext.current

    productViewModel.toast?.let { toast ->
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }

    if (productViewModel.readyToNavigate) {
        LaunchedEffect(true) {
            navController.navigate("ProductsView")
        }
    }


    if (showCamera) {
        Box() {
            CameraView(code, onValueChange = { value -> code = value })
            if (code.isNotEmpty()) {
                showCamera = !showCamera
            }
            Row() {
                Button(onClick = { showCamera = !showCamera }, modifier = Modifier.background(Color.Transparent),) {
                    Text(text = "Close Camera")
                }
            }
        }
    } else {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .heightIn(min = Dp.Unspecified, max = 100.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = 5.dp,
                border = BorderStroke(2.dp, MaterialTheme.colors.primary)
            ) {
                TextField(
                    value = code, onValueChange = { code = it },
                    label = { Text(text = "EAN:") }, modifier =
                    Modifier
                        .height(200.dp)
                        .background(Color.White)
                        .fillMaxWidth(), colors = TextFieldDefaults
                        .textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary)
                )
            }
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {
                    showCamera = !showCamera
                    code = ""
                }) {
                    Text(text = "Open Camera")
                }
                Button(onClick = { productViewModel.addProduct(code) }) {
                    Text(text = "Save product")
                }
            }

        }
    }
}


