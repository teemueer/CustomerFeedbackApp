package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.customerfeedbackapp.models.Feedback

@Composable
fun FeedbackFormView(productViewModel: ProductViewModel) {
    var feedback by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        FeedbackHeader(productName = productViewModel.currentItem2.title ?: "")
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 120.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = 5.dp,
            border = BorderStroke(2.dp, MaterialTheme.colors.primary)
        ) {
            TextField(
                value = feedback, onValueChange = { feedback = it },
                label = { Text(text = "Leave feedback here!") }, modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(), colors = TextFieldDefaults
                    .textFieldColors(backgroundColor = MaterialTheme.colors.onPrimary )
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Rate the product:")
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            for (i in 1..5) {
                Button(onClick = { rating = i }) {
                    Text("$i", fontSize = 20.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
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