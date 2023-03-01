package com.example.customerfeedbackapp.screens.new_product

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.screens.product.ProductViewModel

@Composable
fun NewProductScreen(navController: NavController, mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val viewModel: NewProductViewModel = viewModel()

    if (viewModel.readyToNavigate) {
        LaunchedEffect(true) {
            navController.navigate("home")
        }
    }

    var ean by remember { mutableStateOf("") }

    Column {
        Text("New Product", fontSize = 24.sp)
        TextField(value = ean, onValueChange = { ean = it }, label = { Text("EAN" ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(onClick = {
            viewModel.save(ean)
        }) {
            Text("Save new product")
        }
    }

    viewModel.toast?.let { toast ->
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }
}