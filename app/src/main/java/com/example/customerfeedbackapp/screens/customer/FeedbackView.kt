package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.screens.camera.CameraView

@Composable
fun FeedbackView(productViewModel: ProductViewModel, navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    var cameraOpen by remember { mutableStateOf(false) }
    var code by remember { mutableStateOf("") }


    if (cameraOpen) {
        if (code.isNotEmpty()) {
            for (product in productViewModel.state) {
                if (product.ean!!.contains(code)) {
                    productViewModel.currentItem2 = product
                    navController.navigate("FeedbackFormView")
                    cameraOpen = !cameraOpen
                }
            }
        }
        Box() {
            CameraView(scannerCode = code, onValueChange = { value -> code = value })
            Button(
                onClick = {
                    cameraOpen = !cameraOpen
                },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                shape = RoundedCornerShape(0.dp),
                border = BorderStroke(2.dp, Color.White),
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "RETURN", color = Color.White, fontWeight = FontWeight.ExtraBold)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 0.dp, bottom = 0.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            FeedbackInstruction()
            Spacer(modifier = Modifier.height(10.dp))
            SearchView(state = textState)
            Button(
                onClick = {
                    cameraOpen = !cameraOpen
                }, modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Open camera",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = ptserif_bold
                )
            }
            Column(
                modifier = Modifier
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


}

@Composable
fun FeedbackInstruction() {
    Column {
        Text(
            text = "Feedback:",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = ptserif_bold
        )
    }
}


