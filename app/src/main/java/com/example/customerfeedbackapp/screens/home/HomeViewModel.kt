package com.example.customerfeedbackapp.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Product
import com.example.customerfeedbackapp.models.ProductBeta
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class HomeViewModel(): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    var state = mutableStateOf<List<Product>>(emptyList())
    var state2 = mutableStateOf<List<ProductBeta>>(emptyList())

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

    fun getProducts_v2(){
        val handle = firestore.collection("products_beta").get()
        handle.addOnSuccessListener {
            val _products = ArrayList<ProductBeta>()
            for(product_beta in it.documents){
                product_beta.toObject(ProductBeta::class.java)?.let { _products.add(it) }
            }
            state2.value = _products
        }
    }



}