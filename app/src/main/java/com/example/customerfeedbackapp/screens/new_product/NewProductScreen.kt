package com.example.customerfeedbackapp.screens.new_product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.models.Product

@Composable
fun NewProductScreen(navController: NavController, viewModel: NewProductViewModel) {
    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val product: Product? by viewModel.newProduct.observeAsState(null)

    if (product != null) {
        LaunchedEffect(true) {
            navController.navigate("home")
        }
    }

    Column {
        Text("New Product", fontSize = 24.sp)
        TextField(value = id, onValueChange = { id = it }, label = { Text("Id" ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        TextField(value = name, onValueChange = { name = it }, label = { Text("Name" ) })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Description" ) },
            modifier = Modifier.height(120.dp))
        Button(onClick = {
            viewModel.save(Product(id.toInt(), name, description))
        }) {
            Text("Save new product")
        }
    }
}