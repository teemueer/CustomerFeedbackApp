package com.example.customerfeedbackapp.screens.product

import android.util.Log
import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.Product

@Composable
fun ProductScreen(navController: NavController, mainViewModel: MainViewModel) {
    val viewModel: ProductViewModel = viewModel()

    if (mainViewModel.currentUser.value == null) {
       //RateProduct(viewModel)
        RateProductText(viewModel)
    } else {
        EditProduct(viewModel)
    }
}

/*
@Composable
fun RateProduct(viewModel: ProductViewModel) {
    val product = viewModel.state.value

    Column {
        Text("Feedback for ${product?.title}", fontSize = 24.sp)
        Row {
           /*
            for (i in 1..5) {
                Button(onClick = { viewModel.rate(i) }) {
                    Text("$i", fontSize = 24.sp)
                }
            }

            */
        }
    }
}
*/

@Composable
fun RateProductText(viewModel: ProductViewModel){
    val product = viewModel.product

    var feedback by remember { mutableStateOf("") }

    Column() {
        Text(text = "Leave feedback for ${product?.title}", fontSize = 20.sp)

        product?.images?.first().let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
        }

        TextField(value = feedback, onValueChange = { feedback = it }, label = { Text("Type feedback here!" ) },
            modifier = Modifier.height(120.dp))

        Row {
            for (i in 1..5) {
                Button(onClick = { viewModel.sendFeedback(feedback, i) }) {
                    Text("$i", fontSize = 24.sp)
                }
            }
        }
    }
}



@Composable
fun EditProduct(viewModel: ProductViewModel) {
    val product = viewModel.product

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column {
        Text("Edit product", fontSize = 24.sp)
        TextField(value = if (title != "") title else product?.title ?: "", onValueChange = { title = it })
        TextField(value = if (description != "") description else product?.description ?: "", onValueChange = { description = it },
            modifier = Modifier.height(120.dp))
        Button(onClick = {
            viewModel.update(
                if (title != "") title else product?.title ?: "",
                if (description != "") description else product?.description ?: ""
            )
        }) {
            Text("Update product")
        }
    }
}