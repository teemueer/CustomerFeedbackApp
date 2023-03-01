package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedbackFormView(productViewModel: ProductViewModel, formViewModel: FormViewModel){
    var feedback by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)){
        FeedbackHeader(productName = productViewModel.currentItem.name)
        TextField(value = feedback, onValueChange = {feedback = it},
        label = { Text(text = "Leave feedback here!")}, modifier =
            Modifier
                .height(200.dp)
                .fillMaxWidth())
        Button(onClick = {
            if(feedback.isNotEmpty())
            formViewModel.rate(feedback, productViewModel.currentItem.id)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Send Feedback!")
        }
    }
}

@Composable
fun FeedbackHeader(productName:String){
    Column {
        Text(text ="Palaute tuotteelle:", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp )
        Text(text =productName, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp )
    }
}