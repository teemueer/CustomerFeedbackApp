package com.example.customerfeedbackapp.screens.customer

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore


class ProductViewModel(stateHandle: SavedStateHandle): ViewModel(){
    var currentItem:Product  = Product()
    private var firestore = FirebaseFirestore.getInstance()
    var fb = mutableStateOf<List<Product>>(emptyList())
    var state = ArrayList<Product>()
    var testState = mutableStateOf<Product?>(null)


    fun getProducts(){
        val handle = firestore.collection("products_beta").get()
        handle.addOnSuccessListener {
            val _products = ArrayList<Product>()
            for(product_beta in it.documents){
                product_beta.toObject(Product::class.java)?.let {
                    state.add(it)
                    _products.add(it)
                }
                fb.value = _products
            }
        }
    }



}



