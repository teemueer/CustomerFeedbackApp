package com.example.customerfeedbackapp.screens.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun FeedbackFormView(productViewModel: ProductViewModel){
    Column(){
        Text(text = productViewModel.currentItem.name )
        Text(text = productViewModel.currentItem.description)
        Text(text = productViewModel.currentItem.price)
    }
}