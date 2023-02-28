package com.example.customerfeedbackapp.screens.customer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.R
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore


class ProductViewModel: ViewModel(){
    var currentItem:Product  = Product()
    private var firestore = FirebaseFirestore.getInstance()
    var state = ArrayList<Product>()

    fun getProducts_v2(){
        val handle = firestore.collection("products_beta").get()
        handle.addOnSuccessListener {
            for(product_beta in it.documents){
                product_beta.toObject(Product::class.java)?.let { state.add(it) }
            }
        }
    }
}


