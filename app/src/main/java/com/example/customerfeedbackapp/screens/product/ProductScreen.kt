package com.example.customerfeedbackapp.screens.product

import android.util.Log
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.Product

@Composable
fun ProductScreen(navController: NavController, mainViewModel: MainViewModel) {
    val viewModel: ProductViewModel = viewModel()

    //if (mainViewModel.currentUser.value == null) {
    //    RateProduct(viewModel)
    //} else {
        EditProduct(viewModel)
    //}
}

@Composable
fun RateProduct(viewModel: ProductViewModel) {
    val product = viewModel.state.value

    Column {
        Text("Feedback for ${product?.name}", fontSize = 24.sp)
    }
}

@Composable
fun EditProduct(viewModel: ProductViewModel) {
    val product = viewModel.state.value

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column {
        Text("Edit product", fontSize = 24.sp)
        TextField(value = if (name != "") name else product?.name ?: "", onValueChange = { name = it })
        TextField(value = if (description != "") description else product?.description ?: "", onValueChange = { description = it },
            modifier = Modifier.height(120.dp))
        Button(onClick = {
            viewModel.update(
                if (name != "") name else product?.name ?: "",
                if (description != "") description else product?.description ?: ""
            )
        }) {
            Text("Update product")
        }
    }
}