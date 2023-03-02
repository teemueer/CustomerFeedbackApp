package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FeedbackFormView(productViewModel: ProductViewModel) {
    var feedback by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)) {
        FeedbackHeader(productName = productViewModel.currentItem2.title ?: "")
        TextField(
            value = feedback, onValueChange = { feedback = it },
            label = { Text(text = "Leave feedback here!") }, modifier =
            Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            for (i in 1..5) {
                Button(onClick = { rating = i }) {
                    Text("$i", fontSize = 24.sp)
                }
            }
        }
        Button(onClick = {
            if (feedback.isNotEmpty()) productViewModel.rate(feedback, rating)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Send Feedback!")
        }


    }
}

@Composable
fun FeedbackHeader(productName: String) {
    Column {
        Text(text = "Palaute tuotteelle:", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        Text(text = productName, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
    }
}