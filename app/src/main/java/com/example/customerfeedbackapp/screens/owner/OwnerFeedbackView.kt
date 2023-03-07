package com.example.customerfeedbackapp.screens.owner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.screens.customer.ProductViewModel

@Composable
fun OwnerFeedbackView(productViewModel: ProductViewModel) {
    val feedbacks = productViewModel.currentItem2.feedback.filter {
        it.rating == productViewModel.currentFeedbackSelected && it.feedback != ""
    }

    if (feedbacks.size > 0) {
        LazyColumn() {
            items(feedbacks) { feedback ->
                FeedbackItem(feedback)
            }
        }
    } else {
        Text("No written feedbacks available...")
    }
}

@Composable
fun FeedbackItem(feedback: Feedback) {
    Card(
        modifier = Modifier
            .heightIn(min = Dp.Unspecified, max = 120.dp)
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(feedback.date, color = Color.Gray, fontSize = 10.sp)
            Text(feedback.feedback)
        }
    }
}