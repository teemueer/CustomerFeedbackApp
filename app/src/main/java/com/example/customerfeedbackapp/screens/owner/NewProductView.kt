package com.example.customerfeedbackapp.screens.owner

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.dmsans_regular
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.screens.camera.CameraView
import com.example.customerfeedbackapp.screens.customer.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewProductView(navController: NavController, productViewModel: ProductViewModel) {
    var code by remember { mutableStateOf("") }
    var cameraOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    productViewModel.toast?.let { toast ->
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }

    if (productViewModel.readyToNavigate) {
        LaunchedEffect(true) {
            navController.navigate("ProductsView")
            productViewModel.readyToNavigate = false
        }
    }


    if (cameraOpen) {
        Box {
            CameraView(code, onValueChange = { value -> code = value })
            if (code.isNotEmpty()) {
                cameraOpen = !cameraOpen
            }
            Row {
                Button(
                    onClick = {
                        cameraOpen = !cameraOpen
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    shape = RoundedCornerShape(0.dp),
                    border = BorderStroke(2.dp, Color.White),
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(text = stringResource(R.string.camera_return), color = Color.White, fontWeight = FontWeight.ExtraBold)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .heightIn(min = Dp.Unspecified, max = 100.dp),
                shape = RoundedCornerShape(3.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
            ) {
                TextField(
                    value = code, onValueChange = { code = it },
                    label = { Text(text = stringResource(R.string.ean), fontFamily = dmsans_regular) }, modifier =
                    Modifier
                        .height(200.dp)
                        .background(Color.White)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        cameraOpen = !cameraOpen
                        code = ""
                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.camera_open),
                        fontFamily = ptserif_bold
                    )
                }
                Button(
                    onClick = { productViewModel.addProduct(code) },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(text = stringResource(R.string.save_product), fontFamily = ptserif_bold)
                }
            }

        }
    }
}


