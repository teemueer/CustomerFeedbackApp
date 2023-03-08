package com.example.customerfeedbackapp.screens.owner

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.fonts.dmsans_regular
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.screens.customer.ProductViewModel

@Composable
fun OwnerFeedbackView(productViewModel: ProductViewModel) {
    Log.d("DBG", "${productViewModel.currentFeedbackSelected}")
    val feedbacks = productViewModel.currentItem2.feedback.filter {
        it.rating == productViewModel.currentFeedbackSelected && it.feedback != ""
    }
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        if (feedbacks.isNotEmpty()) {
            LazyColumn() {
                items(feedbacks) { feedback ->
                    FeedbackItem(feedback)
                }
            }
        } else {
            Column() {
                Text(stringResource(id = R.string.no_feedback))
            }
        }
    }
}

@Composable
fun FeedbackItem(feedback: Feedback) {
    Card(
        modifier = Modifier
            .heightIn(min = Dp.Unspecified, max = 120.dp)
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(3.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)

    ) {
        Column(Modifier.padding(10.dp)) {
            Text(feedback.date, color = MaterialTheme.colorScheme.onPrimary, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(feedback.feedback, color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp, fontFamily = dmsans_regular)
        }
    }
}