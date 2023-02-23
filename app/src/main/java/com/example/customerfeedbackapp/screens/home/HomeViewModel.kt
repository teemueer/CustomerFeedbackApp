package com.example.customerfeedbackapp.screens.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.customerfeedbackapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel(): ViewModel() {
    private var firestore = FirebaseFirestore.getInstance()
    var products = MutableLiveData<List<Product>>(emptyList())

    init {
        getProducts()
    }

    fun getProducts() {
        val handle = firestore.collection("products").get()
        handle.addOnSuccessListener {
            val _products = ArrayList<Product>()
            for (product in it.documents) {
                product.toObject(Product::class.java)?.let { _products.add(it) }
            }
            products.postValue(_products)
        }
        handle.addOnFailureListener { Log.d("DBG", "${it.message}") }
    }
}