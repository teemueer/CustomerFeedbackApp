package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackFormView(productViewModel: ProductViewModel, formViewModel: FormViewModel){
    var feedback by remember { mutableStateOf("") }
    Column(){
        Text(text = productViewModel.currentItem.name )
        TextField(value = feedback, onValueChange = {feedback = it},
        label = { Text(text = "Leave feedback here!")}, modifier =
            Modifier
                .height(120.dp)
                .widthIn(min = 100.dp, max = 150.dp))
        Button(onClick = {
            formViewModel.rate(feedback, productViewModel.currentItem.id)
        }) {
            Text(text = "Send Feedback!")
        }
    }
}