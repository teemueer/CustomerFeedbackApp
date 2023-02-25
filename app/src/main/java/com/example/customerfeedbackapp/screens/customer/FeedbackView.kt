package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.customerfeedbackapp.permissions.PermissionViewModel

@Composable
fun FeedbackView(productViewModel: ProductViewModel,  permissionViewModel: PermissionViewModel, navController: NavController){
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text(text = "Leave deedback about a product by either selecting an item from the list or")
        Text(text = "scanning the barcode using the camera on your device!")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Open camera")
        }
        SearchView(state = textState )
        ProductList(navController = navController, productViewModel = productViewModel, state = textState, true)
    }
}