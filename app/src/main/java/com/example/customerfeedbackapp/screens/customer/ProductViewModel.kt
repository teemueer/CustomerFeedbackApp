package com.example.customerfeedbackapp.screens.customer

import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.R

data class Aproduct(
    val name: String,
    val description: String,
    val price: String,
    val kiloOrPer: Boolean,
    val category: Int,
    val barcode: Long,
    val productImage: Int,

    )

class ProductViewModel: ViewModel(){
    var currentItem:Aproduct  = Aproduct(
            "RedBull", "A refreshing energy drink",
            "2,70€", false, 0, 9002490207878, R.drawable.stock_rb
    )
}

