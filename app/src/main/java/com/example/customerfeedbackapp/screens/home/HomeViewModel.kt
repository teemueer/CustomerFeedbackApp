package com.example.customerfeedbackapp.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel(): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    var state = mutableStateOf<List<Product>>(emptyList())

    init {
        Log.d("DBG", "HomeViewModel init")
    }

    fun getProducts() {
        val handle = firestore.collection("products").get()
        handle.addOnSuccessListener {
            val _products = ArrayList<Product>()
            for (product in it.documents) {
                product.toObject(Product::class.java)?.let { _products.add(it) }
            }
            state.value = _products
        }
        handle.addOnFailureListener { Log.d("DBG", "${it.message}") }
    }
}