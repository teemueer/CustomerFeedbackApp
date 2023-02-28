package com.example.customerfeedbackapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.customerfeedbackapp.MainViewModel
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.models.User
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController, mainViewModel: MainViewModel) {
    val user: User? by mainViewModel.currentUser.observeAsState(null)

    val viewModel: HomeViewModel = viewModel()
    //viewModel.getProducts()
    viewModel.getProducts_v2()
    //val products = viewModel.state.value
    val products = viewModel.state2.value

    Scaffold(floatingActionButton = {
        //if (user != null) {
            FloatingActionButton(onClick = { navController.navigate("new_product") }) {
                Icon(Icons.Filled.Add, "New Product")
            }
        //}
    }) {
        Column() {
            TopAppBar() {
                IconButton(onClick = { navController.navigate("settings") }) {
                    Icon(Icons.Filled.Settings, "Settings")
                }
                Text(user?.email ?: "anonymous")
            }
            LazyColumn {
                items(products) { product ->
                    Card(modifier = Modifier.padding(16.dp)) {
                        Column(modifier = Modifier.clickable { navController.navigate("products/${product.id}") }) {
                            Text(product.name, fontSize = 24.sp)
                            Text(product.description)
                        }
                    }
                }
            }
        }
    }
}
