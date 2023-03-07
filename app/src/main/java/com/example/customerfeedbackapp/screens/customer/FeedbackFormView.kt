package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customerfeedbackapp.fonts.dmsans_regular
import com.example.customerfeedbackapp.fonts.ptserif_bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackFormView(productViewModel: ProductViewModel) {
    var feedback by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        FeedbackHeader(productName = productViewModel.currentItem2.title ?: "")
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .heightIn(min = Dp.Unspecified, max = 120.dp),
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            TextField(
                value = feedback, onValueChange = { feedback = it },
                label = { Text(text = "Leave feedback here!", fontFamily = dmsans_regular) },
                modifier =
                Modifier
                    .height(200.dp)
                    .background(Color.White)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Rate the product:", fontWeight = FontWeight.Bold, fontFamily = ptserif_bold)
        Spacer(modifier = Modifier.height(5.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            for (i in 1..5) {

                FeedbackButton(
                    rating = rating,
                    number = i,
                    onValueChange = { value ->
                        rating = value
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (feedback.isNotEmpty()) productViewModel.rate(feedback, rating)
            },
            modifier = Modifier.width(150.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Send", fontFamily = ptserif_bold)
        }


    }
}

@Composable
fun FeedbackHeader(productName: String) {
    Column {
        Text(
            text = "Palaute tuotteelle:",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            fontFamily = ptserif_bold
        )

        Text(
            text = productName,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            fontFamily = dmsans_regular
        )
    }
}

@Composable
fun FeedbackButton(
    rating: Int,
    number: Int,
    onValueChange: (Int) -> Unit,

    ) {
    OutlinedButton(
        onClick = { onValueChange(number) },
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(0.dp),

        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Blue,
            containerColor = if (rating == number) MaterialTheme.colorScheme.primary else
                Color.White
        )
    ) {
        Text(
            text = "$number", color = if (rating == number) Color.White else
                MaterialTheme.colorScheme.primary
        )
    }
}